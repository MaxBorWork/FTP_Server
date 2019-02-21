package controller;

import model.CommandProccess;
import model.LogMessages;
import model.ReplyCode;

public class CommandPASS implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length ==  controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            String password  = messageSplit[controller.FIRST_ARGUMENT_INDEX];
            boolean user = controller.connection.getUser(controller.username, password);

            if (user) {
                controller.log.info(LogMessages.PASSWORD_OK_MESSAGE);
                return ReplyCode.CODE_230;
            } else {
                controller.log.info(LogMessages.WRONG_PASSWORD_MESSAGE);
                return ReplyCode.CODE_530;
            }

        } else {
            controller.log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }
}
