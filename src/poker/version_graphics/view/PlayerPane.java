package poker.version_graphics.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import poker.version_graphics.model.Card;
import poker.version_graphics.model.HandType;
import poker.version_graphics.model.Player;

public class PlayerPane extends VBox {
	private HBox nameBox = new HBox();
    private Label lblName = new Label();
    private Button btnRename = new Button("rename");
    private Button btnRemove = new Button("X");
    private HBox hboxCards = new HBox();
    private Label lblEvaluation = new Label("--");
    
    // Link to player object
    private Player player;
    
    public PlayerPane() {
        super(); // Always call super-constructor first !!
        this.getStyleClass().add("player"); // CSS style class
        
        btnRename.setMaxWidth(80);
        btnRemove.setMaxWidth(20);
        btnRemove.setAlignment(Pos.CENTER_RIGHT);
        Region filler = new Region();
        nameBox.setHgrow(filler, Priority.ALWAYS);
        this.nameBox.getChildren().addAll(lblName, btnRename,filler,  btnRemove);
        
        // Add child nodes
        this.getChildren().addAll(nameBox, hboxCards, lblEvaluation);
        
        // Add CardLabels for the cards
        for (int i = 0; i < 5; i++) {
            Label lblCard = new CardLabel();
            hboxCards.getChildren().add(lblCard);
        }
    }
    
    public void setPlayer(Player player) {
    	this.player = player;
    	player.getIsWinnerProperty().addListener((obs, oldValue, newValue) ->{
    		if(newValue) {
    			this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    		}else {
    			this.setBackground(Background.EMPTY);
    		}
    	});
    	updatePlayerDisplay(); // Immediately display the player information
    }
    
    public void updatePlayerDisplay() {
    	lblName.setText(player.getPlayerName());
    	for (int i = 0; i < Player.HAND_SIZE; i++) {
    		Card card = null;
    		if (player.getCards().size() > i) card = player.getCards().get(i);
    		CardLabel cl = (CardLabel) hboxCards.getChildren().get(i);
    		cl.setCard(card);
    		HandType evaluation = player.evaluateHand();
    		if (evaluation != null)
    			lblEvaluation.setText(evaluation.toString());
    		else
    			lblEvaluation.setText("--");
    	}
    }
    
    public Button getRenameButton() {
    	return this.btnRename;
    }
}
