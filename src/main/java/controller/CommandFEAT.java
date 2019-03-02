package controller;

import model.CommandProcess;
import model.ReplyCode;
import org.apache.log4j.Logger;

public class CommandFEAT implements CommandProcess {

    private Logger log = Logger.getLogger(CommandFEAT.class);

    @Override
    public String process(String message, ReplyCode code, CommandsController controller) {
        return "211-Features:\n" + "UTF8\n" + "211 End";
    }
}
