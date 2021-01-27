import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class WorldController {
    @FXML
	private ImageView imageView;

	@FXML
	private ImageView logicVIew;

    @FXML
    private Text textTitle;

    @FXML
    private Button go;

    @FXML
    private TextArea textMessage;

    @FXML
    private AnchorPane imagePane;

    @FXML
    private ScrollPane itemPane;

    @FXML
    private HBox itemBox;

    private Player player;
    HashMap<Item,ImageView> itemImageViews = new HashMap<>();

    /**
     * Initialize the whole program.
     */
	public void Initialise() {
        player = new Player(); // Initialize the Player
        textMessage.appendText("Welcome to our flat\n");
        imageView.setFitWidth(512);
        imageView.setFitHeight(384);
        setIcons(); // Initialize the items on GUI
        updateView();
	}

    /**
     * When user press 'GO' button:
     * Go Forward
     */
    public void pressGoFoward(ActionEvent event){
	    player.goFoward();
        textMessage.appendText("You've entered the "+player.getCurrentRoomName()+"\n");
        updateView();

    }

    /**
     * When user press '<' button:
     * turn left
     */
    public void pressTurnLeft(ActionEvent event){
        player.turn("Left");
        updateView();
    }

    /**
     * When user press '>' button:
     * turn left
     */
    public void pressTurnRight(ActionEvent event){
        player.turn("Right");
        updateView();
    }

    /**
     * Get image from Player class,
     * update images of the GUI
     */
    public void updateView() {
        String roomName = player.getCurrentRoomName();
        Image image = player.getCurrentImage();
        Image logicMap = player.getCurrentLogicMap();

        go.setDisable(!player.hasNext());
        imageView.setImage(image);
        logicVIew.setImage(logicMap);
        textTitle.setText(roomName);

        showItems();
    }

    /**
     * get item list from player class
     * update the status of the items
     */
    public void showItems(){
        Collection<Item> item_list = player.getItems();
        Collection<Item> items_rest = player.getRestItems();
        if(item_list!=null){
            for(Item item : item_list){
                ImageView icon = itemImageViews.get(item);
                icon.setImage(item.getImage());
            }
        }
        if(items_rest!=null){
            for(Item item :items_rest){
                ImageView icon = itemImageViews.get(item);
                icon.setImage(null);
            }
        }
    }

    /**
     * Initialize the items
     * for each item, create a ImageView called 'icon'
     *
     * If the icon is in itemBox, double click = move it to imagePane
     * If the icon is in imagePane, double click = move it to itemBox
     *
     * When icon is in imagePane, it can be dragged by setOnMouseDragged method.
     */
    public void setIcons(){
        Set<Item> items = player.getAllItems();
        for(Item item:items){
            ImageView icon = new ImageView();
            icon.setFitWidth(64);
            icon.setFitHeight(64);
            icon.setImage(item.getImage());
            icon.setId(item.getName());
            icon.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        if (imagePane.getChildren().contains(icon)) {
                            itemBox.getChildren().add(icon);
                            player.Pick(item);
                            textMessage.appendText("You pick a " + icon.getId() + "\n");
                            showItems();
                        } else {
                            imagePane.getChildren().add(icon);
                            player.Drop(item);
                            textMessage.appendText("You drop a " + icon.getId() + "\n");
                            showItems();
                        }
                    }
                }
            });
            icon.setOnMouseDragged(event -> {
                if(imagePane.getChildren().contains(icon)) {
                    event.consume();
                    double xPosition = event.getSceneX();
                    double yPosition = event.getSceneY();
                    icon.setLayoutX(xPosition - 0.5 * icon.getFitWidth());
                    icon.setLayoutY(yPosition - 0.5 * icon.getFitHeight());
                }
            });

            itemBox.getChildren().add(icon);
            itemImageViews.put(item, icon);

        }

        itemPane.setContent(itemBox);
    }
}
