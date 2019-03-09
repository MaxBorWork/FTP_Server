package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.ServerSocketAccept;

public class CommandPASV implements CommandProcess {

    private Logger log = Logger.getLogger(CommandPASV.class);

    public String process(String message,  ReplyCode code, CommandsController controller){
        controller.loggerConfig(log);
        String[] messageSplit = message.split(Config.SPACE);
        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            log.debug(LogMessages.SENT_ADDRESS_MESSAGE);
            return  code.getCODE_227();
        } else {
            log.debug(LogMessages.WRONG_COMMAND_MESSAGE);
            return CommandsController.reply.codeToMessage.get(500).toString();
        }
    }
}

