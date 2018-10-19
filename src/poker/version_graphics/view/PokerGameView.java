package poker.version_graphics.view;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private ControlArea controls;
	private MenuItem addPlayerItem;
	private BorderPane borderPane;
	
	private GridPane grid;
	
	private final int PLAYER_PER_ROW = 3;
	
	private PokerGameModel model;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		
		MenuBar menuBar = new MenuBar();
		
		Menu configMenu = new Menu("Config");
		addPlayerItem = new MenuItem("Add Player");		
		addPlayerItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));
		
		configMenu.getItems().addAll(addPlayerItem);
		menuBar.getMenus().add(configMenu);	
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls into a BorderPane
		borderPane = new BorderPane();
		resetGame();

		borderPane.setCenter(grid);
		borderPane.setBottom(controls);
		borderPane.setTop(menuBar);	
		
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(true);
		stage.setMaximized(true);
        // Create the scene using our layout; then display it
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        //stage.setHeight(900);
        stage.show();		
	}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) grid.getChildren().get(i);
		//return (PlayerPane) playersBoxes.stream().map(pb -> pb.getChildren()).flatMap(pp -> pp.stream()).collect(Collectors.toList()).get(i);
	}
	
	public Button getShuffleButton() {
		return controls.btnShuffle;
	}
	
	public Button getDealButton() {
		return controls.btnDeal;
	}
	
	public MenuItem getAddPlayerItem() {
		return this.addPlayerItem;
	}
	
	public void resetGame() {
		grid = new GridPane();
		grid.setMinHeight(0);
		int tmpRow = 1;
		for(int i = 0; i< model.getNumberOfPlayers(); i++) {
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			
			if(i % this.PLAYER_PER_ROW  == 0) {
				tmpRow++;
			}
			
			grid.add(pp, i % this.PLAYER_PER_ROW, tmpRow);
		}
		borderPane.setCenter(grid);
	}
}
