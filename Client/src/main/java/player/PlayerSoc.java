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
import playerModel.PlayerModel;

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

        }

    }


    public void setPlayer(Player player) {
        this.player = player;
        try {

            JSONObject jsonMsg = new JSONObject();
            jsonMsg.put("type", "setPlayer");
            jsonMsg.put("player_id", player.getID());

            StringWriter out = new StringWriter();
            jsonMsg.writeJSONString(out);
            ps.println(out.toString());
        } catch (IOException e) {
            System.out.println("Changing json to string failed!!");
        }

    }

    public Player getPlayer() {
        return player;
    }

    private void playRequest() {
        // send invitation alert to a friend
        String fromPlayer_name = jsonMsg.get("from_name").toString();
        Platform.runLater(() -> {
            Alerts.sendRequestAlert(fromPlayer_name);
        });
    }

    private void login() throws ParseException {
        if (jsonMsg.get("status").toString() == "true") {
            Player pl = new Player();
            pl.setID(Integer.parseInt(jsonMsg.get("id").toString()));
            pl.setPlayerName(jsonMsg.get("name").toString());
            App.getPlayerSoc().setPlayer(pl);
            PlayerModel.getPlayers(jsonMsg.get("players").toString());
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

        }

    }

}


