package poker.version_graphics.view;

import javafx.scene.control.Label;
import poker.version_graphics.model.Player;

public class WinnerLabel extends Label {
	public WinnerLabel() {
		super();
		this.getStyleClass().add("winner");
	}
	
	// Bind the label to the winner
	public void setWinner(Player winner) {
		this.textProperty().bind(winner.getPlayerNameProperty());
	}
}