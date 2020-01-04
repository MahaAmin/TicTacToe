package tic.server;

import tic.socket.actions.PlayRequest;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class ServerHandler extends Thread {

    private static Vector<ServerHandler> players;
    private DataInputStream dis;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private PrintStream ps;
    private Socket soc;


    public ServerHandler(Socket socket) {
        players = new Vector<>();
        try {
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            soc = socket;
            players.add(this);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                PlayRequest req = (PlayRequest) ois.readObject();
                if (req != null) {
                    sendMessageToAll(req);
                    System.out.println(req.getRequestType());

//                    sendMessageToAll(msg);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                ps.close();
                dis.close();
                ois.close();
                oos.close();
                soc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToAll(PlayRequest req) {
        for (ServerHandler player : players) {
            try {
                player.oos.writeObject(req);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
