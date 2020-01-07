package player;

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
                        String data = dis.readUTF();
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
            System.out.println(out.toString());
            ps.println(out.toString());
        } catch (IOException e) {
            System.out.println("Changing json to string failed!!");
        }

    }

    public Player getPlayer() {
        return player;
    }

    private void playRequest() {
        String fromPlayer = jsonMsg.get("from_name").toString();
        Platform.runLater(() -> {
            try {
                SwitchTo.onlineListPopUpScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void login() {
        if (jsonMsg.get("status").toString() == "true") {
            PlayerHandler.playerSoc.setPlayer(new Player(
                    Integer.toString(jsonMsg.get("id")),
                    jsonMsg.get("name").toString()
            ));
            // redirect to dashboard
        } else {
            // alert wrong password
        }

    }

//    private void objectHandle(PlayRequest req) {
//        switch (req.getRequestType()) {
//            case REQUEST:
//                Platform.runLater(() -> {
//                    try {
//                        SwitchTo.onlineListPopUpScene();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//
//                break;
//        }
//    }

}


