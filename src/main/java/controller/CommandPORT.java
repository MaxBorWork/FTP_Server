package controller;

import model.CommandProccess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;

public class CommandPORT implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String[] hostAndPorts = messageSplit[1].split(controller.COMMA);
            String host = hostAndPorts[0] +"."+hostAndPorts[1] +"."+ hostAndPorts[2] + "."+hostAndPorts[3];
            int port = Integer.parseInt(hostAndPorts[4])* Config.BIT_SHIFT + Integer.parseInt(hostAndPorts[5]);
            if (port != Config.PORT_20_INT) {
                controller.dataSocket.startThread(port);
            }
            controller.log.info(LogMessages.PORT_COMMAND_MESSAGE);
            return ReplyCode.CODE_200;
        } else return ReplyCode.CODE_501;
    }
}