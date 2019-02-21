package controller;

import model.CommandProccess;
import model.ReplyCode;

import java.io.File;

public class CommandMKD implements CommandProccess {
    public String process(String message, CommandsController controller){

        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File file = new File(controller.ROOT + File.pathSeparator + messageSplit[controller.FIRST_ARGUMENT_INDEX]);

            if (file.mkdir()) {
                return ReplyCode.CODE_200;
            } else {
                return "Error"; //не создали
            }
        } else {
            return ReplyCode.CODE_501;
        }
    }
}
/*
*     public String makeDirectoryCommand(String message) {

        String[] messageSplit = message.split(SPACE);

        if (messageSplit.length == SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File file = new File(ROOT + File.pathSeparator + messageSplit[FIRST_ARGUMENT_INDEX]);

            if (file.mkdir()) {
                return ReplyCode.CODE_200;
            } else {
                return "Error"; //не создали
            }
        } else {
            return ReplyCode.CODE_501;
        }*/
