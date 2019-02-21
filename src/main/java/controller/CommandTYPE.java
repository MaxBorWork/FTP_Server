package controller;

import model.CommandProccess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;

public class CommandTYPE implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split( controller.SPACE);

        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            controller.dataType = messageSplit[1];

            if (controller.dataType.equals(Config.TYPE_A)) {
                controller.log.info(LogMessages.TYPE_OF_DATA_IS_A_MESSAGE);
                return ReplyCode.CODE_200;
            } else if (controller.dataType.equals(Config.TYPE_I)) {
                controller.log.info(LogMessages.TYPE_OF_DATA_IS_I_MESSAGE);
                return ReplyCode.CODE_200;
            } else {
                controller.log.info(LogMessages.WRONG_COMMAND_MESSAGE);
                return ReplyCode.CODE_501;
            }
        }
        else {
            controller.log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_501;
        }
    }
}
