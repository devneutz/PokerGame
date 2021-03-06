package poker.version_graphics.model;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Player implements Comparable<Player> {
    public static final int HAND_SIZE = 5;
    private final SimpleStringProperty playerName = new SimpleStringProperty(); // This is the ID
    private final ArrayList<Card> cards = new ArrayList<>();
    private HandType handType;
    private BooleanProperty isWinner;
    
    public Player(String playerName) {
        this.playerName.set(playerName);
        this.isWinner = new SimpleBooleanProperty();
    }

    public boolean getIsWinner() {
    	return this.isWinner.getValue();
    }
    
    public void setIsWinner(boolean inIsWinner) {
    	this.isWinner.setValue(inIsWinner);
    }
    
    public BooleanProperty getIsWinnerProperty() {
    	return this.isWinner;
    }
    
    public void setPlayerName(String inName) {
    	this.playerName.setValue(inName);
    }
    
    public String getPlayerName() {
        return playerName.getValue();
    }
    
    public SimpleStringProperty getPlayerNameProperty() {
    	return playerName;
    }
    
    public ArrayList<Card> getCards() {
        return cards;
    }
    
    public void addCard(Card card) {
        if (cards.size() < HAND_SIZE) cards.add(card);
    }
    
    public void discardHand() {
        cards.clear();
        handType = null;
    }
    
    public int getNumCards() {
        return cards.size();
    }

    /**
     * If the hand has not been evaluated, but does have all cards, 
     * then evaluate it.
     */
    public HandType evaluateHand() {
        if (handType == null && cards.size() == HAND_SIZE) {
            handType = HandType.evaluateHand(cards);
        }
        return handType;
    }

    /**
     * Hands are compared, based on the evaluation they have.
     */
    @Override
    public int compareTo(Player o) {
    	int tmpCompare = handType.compareTo(o.handType);
    	if(tmpCompare != 0) {
    		return tmpCompare;	
    	}
    	return handType.compareTieBreaker(this.cards, o.cards);    	
    }
}
