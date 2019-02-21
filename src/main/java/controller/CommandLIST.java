package controller;


import model.Command;
import model.CommandProccess;
import model.LogMessages;
import model.ReplyCode;

import java.io.IOException;

public class CommandLIST implements CommandProccess {

    public String process(String message, CommandsController controller){

            String[] messageSplit = message.split(controller.SPACE);
            if (messageSplit.length == controller.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
              //  writer.println("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)");
                controller.dataSocket.createDataConnection(controller.ROOT, Command.LIST);
            } else if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
          //  writer.println("150 ASCII data connection for /etc/root (127.0.0.1,20) (0 bytes)");
                String path = controller.ROOT + "/" + messageSplit[1];
            controller.dataSocket.createDataConnection(path, Command.LIST);
            } else {
                return ReplyCode.CODE_501;
            }
            return ReplyCode.CODE_226;
        }

}
