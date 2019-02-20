package model;

import controller.DataTransferringController;

public class ReplyCode {
    static  private String ROOT = "/etc/ftRoot";
    static public String CODE_501 = "501 Syntax error in parameters or arguments.";
    static public String CODE_500 = "500 Syntax error, command unrecognized.";
    static public String CODE_200 = "200 Command okay.";
    static public String CODE_331 = "331 User name okay, need password.";
    static public String CODE_215 = "215 UNIX system type.";
    static public String CODE_257 = "257 \""+ROOT+"\" is root directory.";
    static public String CODE_220 = "220 Service ready for new user";
    static public String CODE_230 = "230 Guest login ok, access restrictions apply";
    static public String CODE_150 = "150 ";
    static public String CODE_226 = "226 Transfer complete";
    static public String CODE_530 = "530 User can't be logged in";

    static public String CODE_227 = "227 Entering Passive Mode " + DataTransferringController.pasvMessage();
    static public String CODE_425= "425 Can't open data connection.";
    static public String CODE_225 =  "225 Data connection open; no transfer in progress.";
    static public String CODE_250 =  "250 CWD command successful";
    static public String CODE_550 = "550 No such file or directory";
}
