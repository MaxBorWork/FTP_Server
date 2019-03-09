package controller;

import model.*;
import org.apache.log4j.Logger;
import util.DataSocket;
import util.ServerSocketAccept;

import java.io.PrintWriter;

public class CommandPORT implements CommandProcess {

    private Logger log = Logger.getLogger(CommandPORT.class);

    public String process(String message, ReplyCode code, CommandsController controller){
        ServerSocketAccept.loggerConfig(log);
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String[] hostAndPorts = messageSplit[1].split(Config.COMMA);
            if (hostAndPorts[5].contains(")")) {
                hostAndPorts[5] = hostAndPorts[5].replace(")", "");
            }
            int port = Integer.parseInt(hostAndPorts[4])* Config.BIT_SHIFT + Integer.parseInt(hostAndPorts[5]);
            if (port != Config.PORT_20_INT) {
                controller.getDataSocket().startThread(port);
            }
            log.debug(LogMessages.PORT_COMMAND_MESSAGE);
            return  CommandsController.reply.codeToMessage.get(200).toString();
        } else return  CommandsController.reply.codeToMessage.get(501).toString();
    }
}
