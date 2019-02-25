package model;

import controller.DataTransferringController;

public class ReplyCode {
 //   private String CODE_227 = "227 Entering Passive Mode " + DataTransferringController.pasvMessage();

    private String CODE_257 = "257 \"";

    private String CODE_150 = "150 ";



    public String getCODE_257(String path) {
        return CODE_257 + path +"\"";
    }



    public String getCODE_150(String type, String dir, String ip) {
        return CODE_150 + type + " data connection for " + dir + " " + ip ;
    }




}
