package player;

import actions.Alerts;
import actions.App;
import actions.GameConfig;
import actions.PlayRequest;
import com.tictactoe.tictactoefx.GamePlayController;
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
            case "gameStart":
                gameStart();
                break;
            case "requestRejected":
                requestRejected();
                break;
            case "updateBoard":
                updateBoard();
                break;
            case "getall":
                PlayerModel.getPlayers(jsonMsg.get("players").toString());
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
         //   Alerts.sendRequestAlert(jsonMsg);
            try {
                SwitchTo.InvitationRequestPopupScene(jsonMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    private void gameStart() {
        // start the game
        GameConfig.setPlayerX(jsonMsg.get("from_name").toString());
        GameConfig.setPlayerO(jsonMsg.get("to_name").toString());
        // player x play first [from player]
        if (Integer.parseInt(jsonMsg.get("from_id").toString()) == player.getID())
            GameConfig.setTurn(true);
        GameConfig.setMode(2);  // two players mode
        Platform.runLater(() -> {
            try {
                SwitchTo.changeTo(App.getWindow(), 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void requestRejected() {
        // send invitation alert to a friend
        Platform.runLater(() -> {
            Alerts.gameRequestRejected(jsonMsg);
        });
    }

    private void updateBoard() {
        // switch the turn between players
        if (GameConfig.getTurn())
            GameConfig.setTurn(false);
        else
            GameConfig.setTurn(true);

        Platform.runLater(() -> {
            App.getGamePlayController().invokePrintBoard(jsonMsg);
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
            Platform.runLater(() -> {
                Alerts.wrongPasswordAlert();
            });
        }
    }

    private void register() {
        if (jsonMsg.get("status").toString().compareTo("true") == 0) {
            Platform.runLater(() -> {
                try {
                    SwitchTo.changeTo(App.getWindow(), 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            Platform.runLater(() -> {
                Alerts.wrongPasswordAlert();
            });
        }
    }

}


