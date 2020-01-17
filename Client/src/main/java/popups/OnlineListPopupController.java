package popups;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import java.io.FileInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import playerModel.Player;
import playerModel.PlayerModel;


public class OnlineListPopupController implements Initializable {
    @FXML
    private JFXListView<String> onlinePlayersLV;
     JFXButton btn ;
    //Here all usernames are stored
    ObservableList<String> listView = FXCollections.observableArrayList("User 1", "User 2", "User3");
    
    static class Cell extends ListCell<String>
    {
        HBox hbox = new HBox();
        JFXButton btn = new JFXButton("button");
        Label label = new Label("Label");
        Pane pane = new Pane();
      //  Image avatar = new Image("C:\\Users\\Jaxon\\Desktop\\me.jpg");
        //ImageView img = new ImageView(avatar);
        public Cell()
        {
            super();
            btn.setStyle("-fx-background-color:  #ff000;");
            hbox.getChildren().addAll(btn, label);
            hbox.setHgrow(pane, Priority.ALWAYS);
            btn.setOnAction(e -> getListView().getItems().remove(getItem()));
        }
        
        public void updateItem(String name, boolean empty)
        {
            btn.setStyle("-fx-background-color:  #ff000;");
            super.updateItem(name, empty);
            setText(null);
            setGraphic(null);
            
            if(name != null && !empty)
            {
                label.setText(name);
                setGraphic(hbox);
            }
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onlinePlayersLV.setItems(listView);
        
        GridPane pane = new GridPane();
        Label name = new Label("h");
        JFXButton btn = new JFXButton("Button");
        //That's the column display
        pane.add(name, 0, 0);
        pane.add(btn, 0, 3);
        
        onlinePlayersLV.setCellFactory(param -> new Cell());

    }    
    
}
