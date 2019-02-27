package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

    static public String IP_ADDRESS_STRING_POINTS;
    static public String IP_ADDRESS_STRING_COMMAS;
    static public String PORT_21_STRING;
    static public int PORT_21_INT;
    static public int MAX_CONNECTION_NUMBER ;
    static public String PORT_20_STRING;
    static public int PORT_20_INT;
    static public int BIT_SHIFT;
    static public String UTF_8;
    static public String TYPE_A;
    static public String TYPE_I;
    static public int SIZE_OF_COMMAND_WITHOUT_ARGUMENT;
    static public int SIZE_OF_COMMAND_WITH_ONE_ARGUMENT;
    static public int SIZE_OF_COMMAND_WITH_TWO_ARGUMENT;
    static public int FIRST_ARGUMENT_INDEX;
    static public String ROOT;
    static public String TEMP;
    static public String SPACE;
    static public String COMMA;
    static public final String SQLITE_URL = "jdbc:sqlite:main.db";
    static public final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS User (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username varchar(255) NOT NULL,\n"
            + "	password varchar(255) NOT NULL\n"
            + ");";

    public Config(){
        try{
            String text = new String(Files.readAllBytes(Paths.get("src/main/resources/Config.json")), StandardCharsets.UTF_8);
            JSONObject object = new JSONObject(text);


           IP_ADDRESS_STRING_POINTS = object.get("IP_ADDRESS_STRING_POINTS").toString();
           IP_ADDRESS_STRING_COMMAS = object.get("IP_ADDRESS_STRING_COMMAS").toString();
           PORT_21_STRING = object.get("PORT_21_STRING").toString();
           PORT_21_INT = (int) object.get("PORT_21_INT");
           MAX_CONNECTION_NUMBER =  (int) object.get("MAX_CONNECTION_NUMBER");
           PORT_20_STRING = object.get("PORT_20_STRING").toString();
           PORT_20_INT = (int) object.get("PORT_20_INT");
           BIT_SHIFT = (int) object.get("BIT_SHIFT");
           UTF_8 = object.get("UTF_8").toString();
           TYPE_A = object.get("TYPE_A").toString();
           TYPE_I = object.get("TYPE_I").toString();
           SIZE_OF_COMMAND_WITHOUT_ARGUMENT = (int)  object.get("SIZE_OF_COMMAND_WITHOUT_ARGUMENT");
           SIZE_OF_COMMAND_WITH_ONE_ARGUMENT = (int)  object.get("SIZE_OF_COMMAND_WITH_ONE_ARGUMENT");
           SIZE_OF_COMMAND_WITH_TWO_ARGUMENT = (int)  object.get("SIZE_OF_COMMAND_WITH_TWO_ARGUMENT");
           FIRST_ARGUMENT_INDEX = (int)  object.get("FIRST_ARGUMENT_INDEX");
           ROOT = object.get("ROOT").toString();
           TEMP = object.get("TEMP").toString();
           SPACE = object.get("SPACE").toString();
           COMMA = object.get("COMMA").toString();

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
