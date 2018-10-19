package poker.version_graphics.controller;

import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import poker.version_graphics.PokerGame;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.DeckOfCards;
import poker.version_graphics.model.Player;
import poker.version_graphics.model.PokerGameModel;
import poker.version_graphics.view.PlayerPane;
import poker.version_graphics.view.PokerGameView;

public class PokerGameController {
	private PokerGameModel model;
	private PokerGameView view;
	
	public PokerGameController(PokerGameModel model, PokerGameView view) {
		this.model = model;
		this.view = view;
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() );
		
		
		view.getAddPlayerItem().setOnAction(e -> {
			try{
				TextInputDialog dialog = new TextInputDialog("1");
				dialog.setTitle("Add players");
				dialog.setHeaderText("Enter the amount of player to be added");
				dialog.setContentText("Players:");
				 
				Optional<String> result = dialog.showAndWait();
				 
				result.ifPresent(number -> {
				    int tmpAmount = Integer.parseInt(number);
				    model.addPlayer(tmpAmount);

					view.resetGame();
				});
			}catch(NumberFormatException inE){
				Alert alert = new Alert(AlertType.ERROR, "Input not a number");
				alert.showAndWait();
			}catch(IllegalArgumentException inEx) {
				Alert alert = new Alert(AlertType.ERROR, inEx.getMessage());
				alert.showAndWait();
			}
		});
		
		// ?????
		for(int i = 0; i < model.getNumberOfPlayers(); i++) {
			view.getPlayerPane(i).getRenameButton().setOnAction(e ->{
				
			});		
		}
	}
	


    /**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	for (int i = 0; i < model.getNumberOfPlayers(); i++) {
    		Player p = model.getPlayer(i);
    		p.setIsWinner(false);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	}

    	model.getDeck().shuffle();
    }
    
    private void evaluateWinner() {
    	Player winner = model.getPlayer(0);
    	
    	for(int i = 0; i < model.getNumberOfPlayers(); i++) {
    		Player playerCompareTo = model.getPlayer(i);
    		playerCompareTo.setIsWinner(false);
    		if(playerCompareTo.compareTo(winner) > 0) {
    			winner = playerCompareTo;
    		}
    	}
    	winner.setIsWinner(true);	
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int cardsRequired = model.getNumberOfPlayers() * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	for (int i = 0; i < model.getNumberOfPlayers(); i++) {
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        		PlayerPane pp = view.getPlayerPane(i);
        		pp.updatePlayerDisplay();
        	}
    		evaluateWinner();
    	} else {
            Alert alert = new Alert(AlertType.ERROR, "Not enough cards - shuffle first");
            alert.showAndWait();
    	}
    }
}
