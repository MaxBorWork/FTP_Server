package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;

public class CommandRMD implements CommandProcess {

    private Logger log = Logger.getLogger(CommandRMD.class);

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){

            String[] messageSplit = message.split(Config.SPACE);

          //  if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
        String fullPath = message.substring(message.indexOf(" ")+1);
       //         String fullPath = messageSplit[Config.FIRST_ARGUMENT_INDEX];
                if (!fullPath.contains(Config.ROOT)) {
                    fullPath = controller.getCurrentDir() + "/" + fullPath;
                }
                File dir = new File(fullPath);

                if (dir.isDirectory() && dir.delete()) {
                    return controller.reply.codeToMessage.get(250).toString();
                } else if (dir.isDirectory() && !dir.delete()) {
                    removeNotNullDir(dir, controller);
                    if (dir.delete()) {
                        return  controller.reply.codeToMessage.get(250).toString();
                    } else {
                        return controller.reply.codeToMessage.get(550).toString();
                    }
                } else {
                    return  controller.reply.codeToMessage.get(550).toString();
                }
         //   } else return code.getCODE_501();

    }

    private void removeNotNullDir(File dir, CommandsController controller) {
        String[] fileNames = dir.list();
        if (fileNames != null) {
            int removedFromDirFilesCount = 0;
            for (String fileName : fileNames) {
                File file = new File(dir.getPath(),fileName);
                if (file.delete()) {
                    log.info("file " + fileName + " was successfully deleted");
                    removedFromDirFilesCount++;
                } else {
                    log.info("can't remove file " + fileName);
                }
            }
            log.info("was successfully removed " + removedFromDirFilesCount + " files");
        }
    }
}
