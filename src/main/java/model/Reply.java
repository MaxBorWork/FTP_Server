package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Reply {
    public Map codeToMessage;

    public Reply(){
        codeToMessage = new TreeMap<Integer, String>();

        try{
            String text = new String(Files.readAllBytes(Paths.get("src/main/resources/Reply_Code.json")), StandardCharsets.UTF_8);
            JSONObject object = new JSONObject(text);
            JSONArray ar = object.getJSONArray("replies");

            for(int i=0; i< ar.length(); ++i){
                JSONObject obj =  ar.getJSONObject(i);
                codeToMessage.put(obj.get("code"), obj.get("message"));
            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
