package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.JDBCConnection;
import util.ServerSocketAccept;

import java.io.PrintWriter;

public class CommandUSER implements CommandProcess {

    private Logger log = Logger.getLogger(CommandUSER.class);

    public String process(String message,  ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);
        ServerSocketAccept.loggerConfig(log);
        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            String username = messageSplit[Config.FIRST_ARGUMENT_INDEX];
            boolean user = controller.getConnection().getUsername(username);
            if (user) {
                controller.setUsername(username);
                log.debug(LogMessages.USERNAME_OK_MESSAGE);
                return  CommandsController.reply.codeToMessage.get(331).toString();
            } else {
                log.debug(LogMessages.NO_SUCH_USER_MESSAGE);
                return  CommandsController.reply.codeToMessage.get(530).toString();
            }
        } else {
            log.debug(LogMessages.WRONG_COMMAND_MESSAGE);
            return  CommandsController.reply.codeToMessage.get(500).toString();
        }
    }
}
