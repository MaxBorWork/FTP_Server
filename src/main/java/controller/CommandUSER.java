package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.JDBCConnection;

import java.io.PrintWriter;

public class CommandUSER implements CommandProcess {

    private Logger log = Logger.getLogger(CommandUSER.class);

    public String process(String message,  ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {

            String username = messageSplit[Config.FIRST_ARGUMENT_INDEX];
            boolean user = controller.getConnection().getUsername(username);
            if (user) {
                controller.setUsername(username);
                log.info(LogMessages.USERNAME_OK_MESSAGE);
                return  controller.reply.codeToMessage.get(331).toString();
            } else {
                log.info(LogMessages.NO_SUCH_USER_MESSAGE);
                return  controller.reply.codeToMessage.get(530).toString();
            }
        } else {
            log.info(LogMessages.WRONG_COMMAND_MESSAGE);
            return  controller.reply.codeToMessage.get(500).toString();
        }
    }
}
