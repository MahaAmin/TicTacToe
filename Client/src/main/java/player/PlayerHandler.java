package player;

import actions.PlayRequest;
import actions.RequestType;


import java.io.IOException;

public interface PlayerHandler {

    public final static PlayerSoc player=new PlayerSoc();

    public static void sendPlayRequest() throws IOException {
        PlayRequest plr=new PlayRequest(1,2, RequestType.REQUEST);
        player.oos.writeObject(plr);
    }

}
