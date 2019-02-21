package controller;

import model.Command;
import model.CommandProccess;
import model.ReplyCode;

import static controller.CommandsController.ROOT;

public class CommandRETR implements CommandProccess {

    public String process(String message, CommandsController controller) {
        String[] messageSplit = message.split(" ");
        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String filename = messageSplit[controller.FIRST_ARGUMENT_INDEX];
            if (!filename.contains("/")) {
                filename = ROOT + "/" + messageSplit[controller.FIRST_ARGUMENT_INDEX];
            }
          //  writer.println("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)");
            controller.dataSocket.createDataConnection(filename, Command.RETR);
        } else return ReplyCode.CODE_501;
        return ReplyCode.CODE_226;
    }

    }
