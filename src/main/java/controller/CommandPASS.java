package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.ServerSocketAccept;

import java.io.PrintWriter;

public class CommandPASS implements CommandProcess {

    private Logger log = Logger.getLogger(CommandPASS.class);

    public String process(String message,  ReplyCode code, CommandsController controller){
        controller.loggerConfig(log);
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length ==  Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            String password  = messageSplit[Config.FIRST_ARGUMENT_INDEX];
            boolean user = controller.getConnection().getUser(controller.getUsername(), password);

            if (user) {
                log.debug(LogMessages.PASSWORD_OK_MESSAGE);
                return  CommandsController.reply.codeToMessage.get(230).toString();
            } else {
                log.debug(LogMessages.WRONG_PASSWORD_MESSAGE);
                return  CommandsController.reply.codeToMessage.get(530).toString();
            }

        } else {
            log.debug(LogMessages.WRONG_COMMAND_MESSAGE);
            return  CommandsController.reply.codeToMessage.get(500).toString();
        }
    }
}
