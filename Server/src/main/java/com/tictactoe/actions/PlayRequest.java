package com.tictactoe.actions;

import com.tictactoe.server.ServerHandler;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.util.Map;
import java.util.Set;

public class PlayRequest  {


    public static boolean sendJSON(Map<String,String> fields, ServerHandler serverHandler){
        JSONObject jsonMsg = new JSONObject();
        // Returns Set view
        Set<Map.Entry<String, String>> st = fields.entrySet();

        for (Map.Entry<String, String> field : st) {
            jsonMsg.put(field.getKey(), field.getValue());
        }

//            StringWriter out = new StringWriter();
//            jsonMsg.writeJSONString(out);
        serverHandler.ps.println(jsonMsg.toJSONString());
        return true;
    }

}
