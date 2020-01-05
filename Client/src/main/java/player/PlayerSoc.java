package player;

import actions.PlayRequest;
import com.tictactoe.tictactoefx.SwitchTo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;

public class PlayerSoc {

    public Socket socket;
    public DataInputStream dis;
    public PrintStream ps;
    public ObjectOutputStream oos;
    public ObjectInputStream ois;

    public PlayerSoc() {
        startConnection();
    }


    private void startConnection() {
            try {
                socket = new Socket("127.0.0.1", 5005);
                dis = new DataInputStream(socket.getInputStream());
                ps = new PrintStream(socket.getOutputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
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
                        PlayRequest req = (PlayRequest) ois.readObject();
                        if (req != null) {
                            objectHandle(req);
                        }else {
                            // receive JSON
                            String data = dis.readUTF();
                            if (!data.isEmpty()) {
                                jsonHandle(data);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        dis.close();
                        ps.close();
                        oos.close();
                        ois.close();
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private void jsonHandle(String data) {

    }

    private void objectHandle(PlayRequest req) {
        switch (req.getRequestType()) {
            case REQUEST:
                Platform.runLater(() -> {
                    try {
                        SwitchTo.onlineListPopUpScene();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                break;
        }
    }

}


