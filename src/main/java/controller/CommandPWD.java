package controller;


import model.CommandProccess;
import model.LogMessages;
import model.ReplyCode;

import java.io.File;

public class CommandPWD implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            controller.log.info(LogMessages.PRINT_ROOT_DIRECTORY_MESSAGE + controller.ROOT);
            return ReplyCode.CODE_257;
        } else {
            controller.log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return ReplyCode.CODE_500;
        }
    }
}
/*
*
    public String printWorkDirCommand(){
        log.info("printing root directory, directory is " + ROOT);
        return ReplyCode.CODE_257;
    }*/
