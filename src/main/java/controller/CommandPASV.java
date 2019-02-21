package controller;


import model.CommandProccess;
import model.LogMessages;
import model.ReplyCode;

public class CommandPASV implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split(controller.SPACE);
        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            controller.log.info(LogMessages.SENT_ADDRESS_MESSAGE);
//            dataSocket.startThread(Config.PORT_20_INT);
            return ReplyCode.CODE_227;
        } else {
            controller.log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }
}

