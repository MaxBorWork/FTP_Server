package controller;

import model.CommandProcess;
import model.ReplyCode;
import org.apache.log4j.Logger;

public class CommandFEAT implements CommandProcess {

    private Logger log = Logger.getLogger(CommandFEAT.class);

    @Override
    public String process(String message, ReplyCode code, CommandsController controller) {
        return CommandsController.reply.codeToMessage.get(211).toString();
    }
}
