package poker.version_graphics.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    
    protected int compareTieBreaker(ArrayList<Card> inCardsOne, ArrayList<Card> inCardsTwo) {
    	// Since the compareTo method on Enums is final, we do the tie break comparison here. another solution would be writing a 
    	// custom Comparator<HandType>
    	Collections.sort(inCardsOne, new Comparator<Card>() {
    		@Override
    		public int compare(Card inCard1, Card inCard2) {
    			return inCard2.getRank().ordinal() - inCard1.getRank().ordinal();
    		}
    	}); 
    	int tmpHighestCardOne = inCardsOne.get(0).getRank().ordinal();
    	
    	Collections.sort(inCardsTwo, new Comparator<Card>() {
    		@Override
    		public int compare(Card inCard1, Card inCard2) {
    			return inCard2.getRank().ordinal() - inCard1.getRank().ordinal();
    		}
    	}); 
    	int tmpHighestCardTwo = inCardsTwo.get(0).getRank().ordinal();
    	
    	// Source for tie-breaks: https://www.adda52.com/poker/poker-rules/cash-game-rules/tie-breaker-rules
    	switch(this) {
    			// Highest Triplet wins
    		case FullHouse:
    			// in this case, the triplet is lower than the pair. setting the highest card to the triplet for evaluation
    			if(tmpHighestCardOne != inCardsOne.get(2).getRank().ordinal()) {
    				tmpHighestCardOne = inCardsOne.get(2).getRank().ordinal();
    			}
    			if(tmpHighestCardTwo != inCardsTwo.get(2).getRank().ordinal()) {
    				tmpHighestCardTwo = inCardsTwo.get(2).getRank().ordinal();
    			}
				//Only one possible tie break -> both have the same high card
        	case StraightFlush:
    			// No tie break possible -> greater value wins
    		case FourOfAKind:
    			return tmpHighestCardOne - tmpHighestCardTwo;
    			// Compare the highest cards until not the same. all five cards same value -> tie break
    		case HighCard:
    		case Flush:
    			for(int i = 0; i < inCardsOne.size(); i++) {
    				if(inCardsOne.get(i).getRank().ordinal() != inCardsTwo.get(i).getRank().ordinal()) {
    					return inCardsOne.get(i).getRank().ordinal() - inCardsTwo.get(i).getRank().ordinal();
    				}
    			}
    			return 0;
    		case OnePair:
    			int tmpPairValueOne = getPairValue(inCardsOne);
    			int tmpPairValueTwo = getPairValue(inCardsTwo);

    			int tmpCompareValueOne = tmpPairValueOne;
    			int tmpCompareValueTwo = tmpPairValueTwo;
    			if(tmpCompareValueOne == tmpCompareValueTwo) {
    				// Use kicker
    				int tmpKickerOne = 0;
    				int tmpKickerTwo = 0;
    				for(int i = 1; i < 4; i++) {
    					tmpKickerOne = getCardValue(inCardsOne, i, tmpPairValueOne);
    					tmpKickerTwo = getCardValue(inCardsTwo, i, tmpPairValueTwo);
    					if(tmpKickerOne != tmpKickerTwo) {
    						return tmpKickerOne - tmpKickerTwo;
    					}
    				}
    				return 0;
    			}
    			return tmpCompareValueOne - tmpCompareValueTwo;    			
        	case TwoPair:
        	    return 0;
        	case ThreeOfAKind:
        		// Third card is always one of the three
        		int tmpThreeOfAKindValueOne = inCardsOne.get(2).getRank().ordinal();
        		int tmpThreeOfAKindValueTwo = inCardsTwo.get(2).getRank().ordinal();        		
        		
        		// in this type of poker, there is no possibility in two players having the same ThreeOfAKind
        	    return tmpThreeOfAKindValueOne - tmpThreeOfAKindValueTwo;
        	    // the highest ending card wins
        	case Straight:
        	    return inCardsOne.get(4).getRank().ordinal() - inCardsTwo.get(4).getRank().ordinal();       	    	    	    			
    		
    	}
    	return 0;
    }
    
    // inLevel => 1 = highest card, 2 = second highest and so on
    // inVetoOrdinal => which number can't be the highest
    private static int getCardValue(ArrayList<Card> inCards, int inLevel, int inVetoOrdinal) {
    	if(inLevel == 0) {
    		return 0;
    	}
    	
    	ArrayList<Card> tmpClonedCards = (ArrayList<Card>)inCards.clone();
    	
    	tmpClonedCards.removeIf(c -> c.getRank().ordinal() == inVetoOrdinal);
    	
    	Collections.sort(tmpClonedCards, new Comparator<Card>() {
    		@Override
    		public int compare(Card inCard1, Card inCard2) {
    			return inCard2.getRank().ordinal() - inCard1.getRank().ordinal();
    		}
    	});
    	
    	return tmpClonedCards.get(inLevel - 1).getRank().ordinal();
    }
    
    
    private static int getPairValue(ArrayList<Card> inCards) {
    	if(!isOnePair(inCards)) {
    		return 0;
    	}
    	
    	for (int i = 0; i < inCards.size() - 1 ; i++) {
            for (int j = i+1; j < inCards.size() ; j++) {
                if (inCards.get(i).getRank() == inCards.get(j).getRank()) {
                	return inCards.get(i).getRank().ordinal();
                }
            }
        }
    	return 0;
    }
}
