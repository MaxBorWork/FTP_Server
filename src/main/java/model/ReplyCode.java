package model;

public class ReplyCode {
    static  private String ROOT = "/etc/ftRoot";
    static public String CODE_501 = "501 Syntax error in parameters or arguments.";
    static public String CODE_500 = "500 Syntax error, command unrecognized.";
    static public String CODE_200 = "200 Command okay.";
    static public String CODE_331 = "331 User name okay, need password.";
    static public String CODE_215 = "215 UNIX system type.";
    static public String CODE_257 = "257 \""+ROOT+"\" is root directory.";
    static public String CODE_220 = "220 Service ready for new user";
}
