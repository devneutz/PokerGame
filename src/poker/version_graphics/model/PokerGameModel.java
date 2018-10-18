package poker.version_graphics.model;

import java.util.ArrayList;

import poker.version_graphics.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private int NumberOfPlayers;
	
	public PokerGameModel(int inNumberOfPlayers) {
		this.NumberOfPlayers = inNumberOfPlayers;
		
		for (int i = 0; i < NumberOfPlayers; i++) {
			players.add(new Player("Player " + i));
		}
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	
	public void addPlayer(int inPlayerToAdd) throws IllegalArgumentException
	{
		if(this.NumberOfPlayers + inPlayerToAdd <= 10) {
			this.NumberOfPlayers += inPlayerToAdd;
			for(int i = 0; i < inPlayerToAdd; i++) {
				players.add(new Player("Player " + players.size()));
			}
			return;
		}
		throw new IllegalArgumentException("Sorry, you tried to start a game with " + (this.NumberOfPlayers + inPlayerToAdd) + " Players. This is not possible.");
	}
	
	public int getNumberOfPlayers() {
		return this.NumberOfPlayers;
	}
}
