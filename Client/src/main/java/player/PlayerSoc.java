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
    private Player player;
    private JSONObject jsonMsg;

    public PlayerSoc() {
        startConnection();
    }


    private void startConnection() {
        try {
            socket = new Socket("192.168.43.76", 5005);
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
//                oos = new ObjectOutputStream(socket.getOutputStream());
//                ois = new ObjectInputStream(socket.getInputStream());
            receiveGameThread();

        } catch (IOException e) {
            System.out.println("error2");
            e.printStackTrace();
        }
    }


    @FXML
    private void receiveGameThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread");
                    while (true) {
                        // receive JSON
                        String data = dis.readLine();
                        if (!data.isEmpty()) {
                            jsonHandle(data);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("error1");
                    e.printStackTrace();

                } catch (ParseException e) {
                    System.out.println("error10");
                    e.printStackTrace();

                } catch (Exception ex) {
                    /** if server close show alert to all players and close the app */
                    closeSocket();
                    Platform.runLater(() -> {
                        Alerts.serverIsShuttingDown();
                        App.getWindow().close();

                    });
                }

            }
        }).start();
    }

    private void closeSocket() {
        try {
            dis.close();
            ps.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("error3");
            ex.printStackTrace();
        }
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
                System.out.println("This is sending the update");
                PlayerModel.getPlayers(jsonMsg.get("players").toString());
                break;
            case "saveGameRequest":
                saveGameRequest();
                break;
            case "saveGameAnswer":
                saveGameAnswer();
                break;
            case "chooseGame":
                chooseGame();
                break;
        }

    }

    /**
     * after player became login save his data is his socket object
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * to use player login data in anywhere
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * popup alert for player2 to accept or refuse a new game request
     * popup alert for player2 to accept or refuse resume old game
     */
    private void playRequest() {
        Platform.runLater(() -> {

            try {
                GameConfig.setRequestPobUpJson(jsonMsg);
                SwitchTo.InvitationRequestPopupScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Alerts.sendRequestAlert(jsonMsg);

        });
    }

    /**
     * popup alert for player1 to choose game type [old or new]
     */
    private void chooseGame() {
        Platform.runLater(() -> {
            Alerts.chooseGameAlert(jsonMsg);
        });
    }


    /**
     * switch to game scene for the 2 players
     */
    private void gameStart() {
        // start the game
        GameConfig.setPlayerX(jsonMsg.get("from_name").toString());
        GameConfig.setPlayerO(jsonMsg.get("to_name").toString());
        if (jsonMsg.containsKey("old_game")) {
            System.out.println("start old game");
            GameConfig.setXOList(jsonMsg.get("board").toString());
        }
        // player x play first [from player]
        if (Integer.parseInt(jsonMsg.get("from_id").toString()) == player.getID())
            GameConfig.setTurn(true);
        GameConfig.setMode(2);  // two players mode
        Platform.runLater(() -> {
            try {
                SwitchTo.changeTo(App.getWindow(), 3);
            } catch (IOException e) {
                System.out.println("error4");
                e.printStackTrace();

            }
        });
    }

    /**
     * for player1
     * popup alert when player2 refused new/old game request from player1
     */
    private void requestRejected() {
        // inform play1 that players2 rejected his request to play a game
        Platform.runLater(() -> {
            // Alerts.gameRequestRejected(jsonMsg);
            try {
                SwitchTo.RequestRejectedPopupScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * for both players
     * update the game board after each turn
     */
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

    /**
     * for both players
     * each player can send a request to save the game and resume it later
     */
    private void saveGameRequest() {
        Platform.runLater(() -> {
            //    Alerts.saveGameAlert(jsonMsg);
        });
    }

    /**
     * for both players
     * the second player can accept or refuse first player save game request
     */
    private void saveGameAnswer() {
        Platform.runLater(() -> {
            boolean isAccepted = false;
            if (jsonMsg.get("response").equals("true")) {
                isAccepted = true;
            }
            System.out.println("save answer " + jsonMsg.get("response"));
            System.out.println("save answer " + isAccepted);
            Alerts.saveGameAnswerAlert(isAccepted);
            if (isAccepted) {
                try {
                    SwitchTo.changeTo(App.getWindow(), 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
                    System.out.println("error5");
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

                    System.out.println("error6");
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


