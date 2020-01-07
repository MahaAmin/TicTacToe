package actions;

import org.json.simple.JSONObject;
import player.PlayerHandler;
import player.PlayerSoc;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class PlayRequest  {

    public static boolean sendJSON(Map<String,String> fields){
        try{
            PlayerSoc player = PlayerHandler.playerSoc;
            JSONObject jsonMsg = new JSONObject();
            // Returns Set view
            Set< Map.Entry< String,String> > st = fields.entrySet();

            for (Map.Entry< String,String> field:st)
            {
                jsonMsg.put(field.getKey(), field.getValue());
            }

            StringWriter out = new StringWriter();
            jsonMsg.writeJSONString(out);
            player.ps.println(out.toString());
            return true;
        }
        catch(IOException e){
            System.out.println("Changing json to string failed!!");
            return false;
        }
    }

}
