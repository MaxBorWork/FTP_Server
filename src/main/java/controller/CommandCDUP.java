package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.ServerSocketAccept;

import java.io.File;
import java.io.PrintWriter;

public class CommandCDUP implements CommandProcess {

    private Logger log = Logger.getLogger(CommandCDUP.class);

    @Override
    public String process(String message,  ReplyCode code, CommandsController controller) {
        controller.loggerConfig(log);
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            String currDirPath = controller.getCurrentDir();
            String updirPath = Config.ROOT;
            StringBuilder b = new StringBuilder(currDirPath);

            String[] currDirPathSplit = currDirPath.split("/");

            if (currDirPathSplit.length > 1) {
                String trimString = "/" + currDirPathSplit[currDirPathSplit.length-1];
                b.replace(currDirPath.lastIndexOf(trimString), currDirPath.lastIndexOf(trimString) + trimString.length(), "" );
                updirPath = b.toString();
            }

            File rootNew = new File(updirPath);

            if(rootNew.isDirectory() && rootNew.exists()){
                log.debug("work directory changed from " + controller.getCurrentDir() + " to " + updirPath);
                controller.setCurrentDir(updirPath);
                return CommandsController.reply.codeToMessage.get(250).toString();

            } else return CommandsController.reply.codeToMessage.get(551).toString();

        } else return CommandsController.reply.codeToMessage.get(501).toString();
    }
}
