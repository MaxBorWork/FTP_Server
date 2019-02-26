package controller;

import model.*;
import org.apache.log4j.Logger;
import util.DataSocket;

import java.io.PrintWriter;

public class CommandPORT implements CommandProcess {

    private Logger log = Logger.getLogger(CommandPORT.class);

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){
        DataSocket dataSocket = new DataSocket();

        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String[] hostAndPorts = messageSplit[1].split(Config.COMMA);
            String host = hostAndPorts[0] +"."+hostAndPorts[1] +"."+ hostAndPorts[2] + "."+hostAndPorts[3];
            int port = Integer.parseInt(hostAndPorts[4])* Config.BIT_SHIFT + Integer.parseInt(hostAndPorts[5]);
            if (port != Config.PORT_20_INT) {
                dataSocket.startThread(port);
            }
            log.info(LogMessages.PORT_COMMAND_MESSAGE);
            return code.getCODE_200();
        } else return code.getCODE_501();
    }
}