package tic.player;

import com.tictactoe.tictactoefx.SwitchTo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import tic.socket.actions.PlayRequest;

import java.io.*;
import java.net.Socket;

public class PlayerSoc {

    public Socket socket;
    public DataInputStream dis;
    public PrintStream ps;
    public ObjectOutputStream oos;
    public  ObjectInputStream ois;

    public PlayerSoc() {
        startConnection();

    }

    public static void main(String[] args) {
        new PlayerSoc();
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
                            switch (req.getRequestType()) {
                                case REQUEST:
                                    Platform.runLater(()->{
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

}


