package controller;

import model.CommandProccess;
import model.LogMessages;
import model.ReplyCode;

import java.io.File;

public class CommandSYST implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length ==  controller.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            controller.log.info(LogMessages.SYSTEM_TYPE_MESSAGE );
            return ReplyCode.CODE_215;
        } else {
            controller.log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }
}
