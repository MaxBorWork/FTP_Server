package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.ServerSocketAccept;

import java.io.File;
import java.io.PrintWriter;

public class CommandRMD implements CommandProcess {

    private Logger log = Logger.getLogger(CommandRMD.class);

    public String process(String message, ReplyCode code, CommandsController controller){
        controller.loggerConfig(log);
        String fullPath = message.substring(message.indexOf(" ")+1);
        if (!fullPath.contains(Config.ROOT)) {
            fullPath = controller.getCurrentDir() + "/" + fullPath;
        }
        File dir = new File(fullPath);

        if (dir.isDirectory() && dir.delete()) {
            return CommandsController.reply.codeToMessage.get(250).toString();
        } else if (dir.isDirectory() && !dir.delete()) {
            removeNotNullDir(dir, controller);
            if (dir.delete()) {
                return  CommandsController.reply.codeToMessage.get(250).toString();
            } else {
                log.debug(LogMessages.WRONG_COMMAND_MESSAGE);
                return CommandsController.reply.codeToMessage.get(550).toString();
            }
        } else {
            log.debug(LogMessages.WRONG_COMMAND_MESSAGE);
            return  CommandsController.reply.codeToMessage.get(550).toString();
        }
    }

    private void removeNotNullDir(File dir, CommandsController controller) {
        String[] fileNames = dir.list();
        if (fileNames != null) {
            int removedFromDirFilesCount = 0;
            for (String fileName : fileNames) {
                File file = new File(dir.getPath(),fileName);
                if (file.delete()) {
                    log.debug("file " + fileName + " was successfully deleted");
                    removedFromDirFilesCount++;
                } else {
                    log.debug("can't remove file " + fileName);
                }
            }
            log.debug("was successfully removed " + removedFromDirFilesCount + " files");
        }
    }
}
