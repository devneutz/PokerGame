package poker.version_graphics.view;

import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import poker.version_graphics.model.PokerGameModel;

public class PokerGameView {
	private ArrayList<HBox> playersBoxes;
	private ControlArea controls;
	private MenuItem addPlayerItem;
	private VBox content;
	
	private final int PLAYER_PER_ROW = 3;
	
	private PokerGameModel model;
	
	public PokerGameView(Stage stage, PokerGameModel model) {
		this.model = model;
		
		MenuBar menuBar = new MenuBar();
		
		Menu configMenu = new Menu("Config");
		addPlayerItem = new MenuItem("Add Player");
		
		configMenu.getItems().addAll(addPlayerItem);
		menuBar.getMenus().add(configMenu);		
		
		// Create all of the player panes we need, and put them into an HBox
		playersBoxes = new ArrayList<HBox>();
		content = new VBox();
		
		drawPlayerPanes();
		
		// Create the control area
		controls = new ControlArea();
		controls.linkDeck(model.getDeck()); // link DeckLabel to DeckOfCards in the logic
		
		// Put players and controls into a BorderPane
		BorderPane root = new BorderPane();
		
		root.setCenter(content);
		root.setBottom(controls);
		root.setTop(menuBar);
		// Disallow resizing - which is difficult to get right with images
		stage.setResizable(true);

        // Create the scene using our layout; then display it
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("poker.css").toExternalForm());
        stage.setTitle("Poker Miniproject");
        stage.setScene(scene);
        stage.setHeight(900);
        stage.show();		
	}
	
	public PlayerPane getPlayerPane(int i) {
		return (PlayerPane) playersBoxes.stream().map(pb -> pb.getChildren()).flatMap(pp -> pp.stream()).collect(Collectors.toList()).get(i);
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
	
	public void drawPlayerPanes() {
		content.getChildren().clear();
		
		playersBoxes.clear();
		playersBoxes.add(new HBox());
		
		HBox currentBox = playersBoxes.get(0);
		
		for (int i = 0; i < model.getNumberOfPlayers(); i++) {
			if(i == this.PLAYER_PER_ROW * playersBoxes.size()) {
				playersBoxes.add(new HBox());
				currentBox = playersBoxes.get(playersBoxes.size() - 1);
			}
			PlayerPane pp = new PlayerPane();
			pp.setPlayer(model.getPlayer(i)); // link to player object in the logic
			currentBox.getChildren().add(pp);
		}

		content.getChildren().addAll(playersBoxes);
	}
}
