package model;

public class Config {
    static public String ROOT = "/";
    static public String TEMP ="/tmp";
    static public String IP_ADDRESS_STRING_COMMAS = "127,0,0,1";

    static public final String IP_ADDRESS_STRING_POINTS = "0.0.0.0";
    static public final String PORT_21_STRING = "21";
    static public final int PORT_21_INT = 21;
    static public final int MAX_CONNECTION_NUMBER = 50;
    static public final String PORT_20_STRING = "20";
    static public final int PORT_20_INT = 20;
    static public final int BIT_SHIFT = 256;
    static public final String UTF_8 ="UTF-8";
    static public final String TYPE_A = "A";
    static public final String TYPE_I = "I";
    static public final int SIZE_OF_COMMAND_WITHOUT_ARGUMENT = 1;
    static public final int SIZE_OF_COMMAND_WITH_ONE_ARGUMENT = 2;
    static public final int SIZE_OF_COMMAND_WITH_TWO_ARGUMENT = 3;
    static public final int FIRST_ARGUMENT_INDEX = 1;
    static public final String SPACE = " ";
    static public final String COMMA = ",";
    static public final String SQLITE_URL = "jdbc:sqlite:main.db";
    static public final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS User (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	username varchar(255) NOT NULL,\n"
            + "	password varchar(255) NOT NULL\n"
            + ");";
}
