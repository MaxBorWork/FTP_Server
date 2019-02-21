package controller;

import model.Command;
import model.CommandProccess;
import model.ReplyCode;

import java.io.File;

import static controller.CommandsController.ROOT;

public class CommandDELE implements CommandProccess {

    public String process(String message, CommandsController controller){

        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File file = new File(ROOT + File.pathSeparator + messageSplit[controller.FIRST_ARGUMENT_INDEX]);

            if (!file.isDirectory() && file.delete()) {
                return ReplyCode.CODE_250;
            } else {
                return "Error"; // либо не удалена либо не файл
            }
        } else {
            return ReplyCode.CODE_501;
        }
    }

}
