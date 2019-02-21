package controller;

import model.CommandProccess;
import model.LogMessages;
import model.ReplyCode;

public class CommandUSER implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            controller.username = messageSplit[controller.FIRST_ARGUMENT_INDEX];
            boolean user = controller.connection.getUsername(controller.username);
            if (user) {
                controller.log.info(LogMessages.USERNAME_OK_MESSAGE);
                return ReplyCode.CODE_331;
            } else {
                controller.log.info(LogMessages.NO_SUCH_USER_MESSAGE);
                return ReplyCode.CODE_530;
            }
        } else {
            controller.log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }
}
