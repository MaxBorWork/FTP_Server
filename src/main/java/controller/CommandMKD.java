package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;

public class CommandMKD implements CommandProcess {

    private Logger log = Logger.getLogger(CommandMKD.class);

    public String process(String message, ReplyCode code, CommandsController controller){

        String fullPath = message.substring(message.indexOf(" ")+1);
            if (!fullPath.contains(Config.ROOT)) {
                fullPath = controller.getCurrentDir() + "/" + fullPath;
            }
            File file = new File(fullPath);
            if (file.mkdir()) {
                return  CommandsController.reply.codeToMessage.get(200).toString();
            } else {
                return  CommandsController.reply.codeToMessage.get(550).toString();
            }
    }
}
