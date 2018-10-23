package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;

public enum HandType {
    HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush;
    
    /**
     * Determine the value of this hand. Note that this does not
     * account for any tie-breaking
     */
    public static HandType evaluateHand(ArrayList<Card> cards) {
        HandType currentEval = HighCard;
        
        if (isOnePair(cards)) currentEval = OnePair;
        if (isTwoPair(cards)) currentEval = TwoPair;
        if (isThreeOfAKind(cards)) currentEval = ThreeOfAKind;
        if (isStraight(cards)) currentEval = Straight;
        if (isFlush(cards)) currentEval = Flush;
        if (isFullHouse(cards)) currentEval = FullHouse;
        if (isFourOfAKind(cards)) currentEval = FourOfAKind;
        if (isStraightFlush(cards)) currentEval = StraightFlush;
        
        return currentEval;
    }
    
    private static boolean isOnePair(ArrayList<Card> cards) {
        boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
                if (cards.get(i).getRank() == cards.get(j).getRank()) found = true;
            }
        }
        return found;
    }
    
    private static boolean isTwoPair(ArrayList<Card> cards) {
        // Clone the cards, because we will be altering the list
        ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();

        // Find the first pair; if found, remove the cards from the list
        boolean firstPairFound = false;
        for (int i = 0; i < clonedCards.size() - 1 && !firstPairFound; i++) {
            for (int j = i+1; j < clonedCards.size() && !firstPairFound; j++) {
                if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank()) {
                    firstPairFound = true;
                    clonedCards.remove(j);  // Remove the later card
                    clonedCards.remove(i);  // Before the earlier one
                }
            }
        }
        // If a first pair was found, see if there is a second pair
        return firstPairFound && isOnePair(clonedCards);
    }
    
    private static boolean isThreeOfAKind(ArrayList<Card> cards) {
    	boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
            	for (int z = j+1; z < cards.size() && !found; z++) {
            		if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(z).getRank()) found = true;
            	}
                
            }
        }
        return found;    
    }
    
    private static boolean isStraight(ArrayList<Card> cards) {
    	boolean tmpStraightFound = false;
    	boolean[] tmpValueFound = new boolean[13];
    	for(Card c : cards) {
    		if(c.getRank().ordinal() == 12) {
    			tmpValueFound[1] = true;
    		}
    		tmpValueFound[c.getRank().ordinal()] = true;
    	}
    	int tmpSuccessiveCards = 0;
    	for(boolean bv : tmpValueFound) {
    		if(bv) {
    			tmpSuccessiveCards++;
    		}else {
    			tmpSuccessiveCards = 0;
    		}
    		
    		if(tmpSuccessiveCards == 5) {
    			tmpStraightFound = true;
    			break;
    		}
    	}
    	return tmpStraightFound;    	
    }
    
    private static boolean isFlush(ArrayList<Card> cards) {
    	 boolean found = false;
         
         for (int i = 1; i < cards.size(); i++)
         {
             if (cards.get(i).getSuit() != cards.get(0).getSuit())
             {
                 return false;
             }

         } found = true;
         return found;
    }
    
    private static boolean isFullHouse(ArrayList<Card> cards) {
    	ArrayList<Card> clonedCards = (ArrayList<Card>) cards.clone();
    	
    	boolean foundall = false; 
    	boolean foundthreeofkind = false;
    	boolean	foundonepair = false;
    	
    	for (int i = 0; i < clonedCards.size() - 1 && !foundthreeofkind; i++) {
            for (int j = i+1; j < clonedCards.size() && !foundthreeofkind; j++) {
            	for (int z = j+1; z < clonedCards.size() && !foundthreeofkind; z++) {
            		if (clonedCards.get(i).getRank() == clonedCards.get(j).getRank() && clonedCards.get(j).getRank() == clonedCards.get(z).getRank()) { 
            			clonedCards.remove(z);
            			clonedCards.remove(j);
            			clonedCards.remove(i);
            			foundthreeofkind = true;
            		}
            	}
                
            }
        }
    	
    	for (int x = 0; x < clonedCards.size() - 1 && !foundonepair; x++) {
            for (int y = x+1; y < clonedCards.size() && !foundonepair; y++) {
                if (clonedCards.get(x).getRank() == clonedCards.get(y).getRank()) foundonepair = true;
            }
        }
    	
    	if(foundthreeofkind && foundonepair) foundall = true;
        
        return foundall;     
    }
    
    private static boolean isFourOfAKind(ArrayList<Card> cards) {
    	boolean found = false;
        for (int i = 0; i < cards.size() - 1 && !found; i++) {
            for (int j = i+1; j < cards.size() && !found; j++) {
            	for (int z = j+1; z < cards.size() && !found; z++) {
            		for (int x = z+1; x < cards.size() && !found; x++) {
            		if (cards.get(i).getRank() == cards.get(j).getRank() && cards.get(j).getRank() == cards.get(z).getRank() && cards.get(z).getRank() == cards.get(x).getRank()) found = true;
            		}
            	}
            }
        }
        return found;
    }
    
    private static boolean isStraightFlush(ArrayList<Card> cards) {
    	boolean found = false;
        
        if(isStraight(cards) && isFlush(cards)) found = true;
    	
        return found;        
    }
}
