package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.PrintWriter;

public class CommandTYPE implements CommandProcess {

    private Logger log = Logger.getLogger(CommandTYPE.class);

    public String process(String message,  ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            controller.setCurrentType(messageSplit[1]);

            if (controller.getCurrentType().equals(Config.TYPE_A)) {
                controller.setCurrentType(Config.TYPE_A);
                log.info(LogMessages.TYPE_OF_DATA_IS_A_MESSAGE);
                return  controller.reply.codeToMessage.get(200).toString();
            } else if (controller.getCurrentType().equals(Config.TYPE_I)) {
                controller.setCurrentType(Config.TYPE_I);
                log.info(LogMessages.TYPE_OF_DATA_IS_I_MESSAGE);
                return  controller.reply.codeToMessage.get(200).toString();
            } else {
                log.info(LogMessages.WRONG_COMMAND_MESSAGE);
                return  controller.reply.codeToMessage.get(501).toString();
            }
        }
        else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return  controller.reply.codeToMessage.get(501).toString();
        }
    }
}
