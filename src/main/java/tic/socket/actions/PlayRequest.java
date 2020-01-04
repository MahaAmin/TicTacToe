package tic.socket.actions;

import java.io.Serializable;

public class PlayRequest  implements Serializable {
    private int from_player;
    private int to_player;
    private RequestType type;

    public PlayRequest(int from, int to, RequestType ty) {
        from_player=from;
        to_player=to;
        type=ty;
    }

    public int getFromPlayer(){
        return from_player;
    }

    public int getToPlayer(){
        return to_player;
    }

    public RequestType getRequestType(){
        return type;
    }

}
