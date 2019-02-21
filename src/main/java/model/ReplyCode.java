package model;

import controller.DataTransferringController;

public class ReplyCode {
    private String CODE_501 = "501 Syntax error in parameters or arguments.";
    private String CODE_500 = "500 Syntax error, command unrecognized.";
    private String CODE_200 = "200 Command okay.";
    private String CODE_331 = "331 User name okay, need password.";
    private String CODE_215 = "215 UNIX system type.";
    private String CODE_257 = "257 \"";
    private String CODE_220 = "220 Service ready for new user";
    private String CODE_230 = "230 Guest login ok, access restrictions apply";
    private String CODE_150 = "150 ";
    private String CODE_226 = "226 Transfer complete";
    private String CODE_530 = "530 User can't be logged in";

    private String CODE_227 = "227 Entering Passive Mode " + DataTransferringController.pasvMessage();
    private String CODE_425= "425 Can't open data connection.";
    private String CODE_225 =  "225 Data connection open; no transfer in progress.";
    private String CODE_250 =  "250 CWD command successful";
    private String CODE_550 = "550 No such file or directory";

    public String getCODE_501() {
        return CODE_501;
    }

    public String getCODE_500() {
        return CODE_500;
    }

    public String getCODE_200() {
        return CODE_200;
    }

    public String getCODE_331() {
        return CODE_331;
    }

    public String getCODE_215() {
        return CODE_215;
    }

    public String getCODE_257(String path) {
        return CODE_257 + path +"\"";
    }

    public String getCODE_220() {
        return CODE_220;
    }

    public String getCODE_230() {
        return CODE_230;
    }

    public String getCODE_150(String type, String dir, String ip) {
        return CODE_150 + type + " data connection for " + dir + " " + ip ;
    }

    public String getCODE_226() {
        return CODE_226;
    }

    public String getCODE_530() {
        return CODE_530;
    }

    public String getCODE_227() {
        return CODE_227;
    }

    public String getCODE_425() {
        return CODE_425;
    }

    public String getCODE_225() {
        return CODE_225;
    }

    public String getCODE_250() {
        return CODE_250;
    }

    public String getCODE_550() {
        return CODE_550;
    }
}
