package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.PrintWriter;

public class CommandSYST implements CommandProcess {

    private Logger log = Logger.getLogger(CommandSYST.class);

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length ==  Config.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            log.info(LogMessages.SYSTEM_TYPE_MESSAGE );
            return  controller.reply.codeToMessage.get(215).toString();
        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return  controller.reply.codeToMessage.get(500).toString();
        }
    }
}
