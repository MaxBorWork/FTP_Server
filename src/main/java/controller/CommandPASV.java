package controller;


import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.PrintWriter;

public class CommandPASV implements CommandProcess {

    private Logger log = Logger.getLogger(CommandPASV.class);

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);
        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            log.info(LogMessages.SENT_ADDRESS_MESSAGE);
            return  code.getCODE_227();
        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return CommandsController.reply.codeToMessage.get(500).toString();
        }
    }
}

