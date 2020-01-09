package player;

import actions.Alerts;
import actions.App;
import actions.PlayRequest;
import com.tictactoe.tictactoefx.SwitchTo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import playerModel.Player;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayerSoc {

    public Socket socket;
    public DataInputStream dis;
    public PrintStream ps;
    //    public ObjectOutputStream oos;
//    public ObjectInputStream ois;
    private Player player;
    private JSONObject jsonMsg;

    public PlayerSoc() {
        startConnection();
    }


    private void startConnection() {
        try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
//                oos = new ObjectOutputStream(socket.getOutputStream());
//                ois = new ObjectInputStream(socket.getInputStream());
            receiveGameThread();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void receiveGameThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // receive JSON
                        String data = dis.readLine();
                        if (!data.isEmpty()) {
                            jsonHandle(data);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        dis.close();
                        ps.close();
//                        oos.close();
//                        ois.close();
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private void jsonHandle(String data) throws ParseException {
        JSONParser parser = new JSONParser();
        jsonMsg = (JSONObject) parser.parse(data);
        System.out.println(data);
        switch (jsonMsg.get("type").toString()) {
            case "playRequest":
                playRequest();
                break;
            case "login":
                login();
                break;
            case "register":
                register();
                break;
        }

    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    private void playRequest() {
        // send invitation alert to a friend
        Platform.runLater(() -> {
            Alerts.sendRequestAlert(jsonMsg);
        });
    }

    private void login() {
        if (jsonMsg.get("status").toString() == "true") {
            Player pl = new Player();
            pl.setID(Integer.parseInt(jsonMsg.get("id").toString()));
            pl.setPlayerName(jsonMsg.get("name").toString());
            App.getPlayerSoc().setPlayer(pl);
            // redirect to dashboard
            Platform.runLater(() -> {
                try {
                    SwitchTo.changeTo(App.getWindow(), 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            // alert wrong password
            Platform.runLater(() -> {
                Alerts.wrongPasswordAlert();
            });
        }
    }

    private void register(){
        System.out.println(jsonMsg.get("status"));
        if(jsonMsg.get("status").toString().compareTo("true")==0){
            Platform.runLater(() -> {
                try {
                    SwitchTo.changeTo(App.getWindow(), 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            Platform.runLater(() -> {
                Alerts.wrongPasswordAlert();
            });
        }
    }

}


