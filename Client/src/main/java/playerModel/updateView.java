package playerModel;

import actions.App;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Map;

public class updateView {
    public static ObservableList<Player> players;
    public static ObservableList<Player> GetObservable(){
        players= FXCollections.observableArrayList();
        return players;
    }
    public static void updatePlayer(ArrayList<Player> map){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                players.clear();
                for (int i=0;i<map.size();++i) {
                    if(map.get(i).getID()== App.getPlayerSoc().getPlayer().getID())
                        continue;
                    players.add(map.get(i));
                }
            }
        });
    }
}
