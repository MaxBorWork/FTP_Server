package controller;

import model.CommandProccess;
import model.LogMessages;
import model.ReplyCode;

import java.io.File;

public class CommandRMD implements CommandProccess {

    public String process(String message, CommandsController controller){

            String[] messageSplit = message.split(controller.SPACE);

            if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
                File dir = new File(controller.ROOT + File.pathSeparator + messageSplit[controller.FIRST_ARGUMENT_INDEX]);

                if (dir.isDirectory() && dir.delete()) {
                    return ReplyCode.CODE_250;
                } else if (dir.isDirectory() && !dir.delete()) {
                    removeNotNullDir(dir, controller);
                    if (dir.delete()) {
                        return ReplyCode.CODE_250;
                    } else {
                        return ReplyCode.CODE_550;
                    }
                } else {
                    return ReplyCode.CODE_550;
                }
            } else {
                return ReplyCode.CODE_501;
            }
    }

    private void removeNotNullDir(File dir, CommandsController controller) {
        String[] fileNames = dir.list();
        if (fileNames != null) {
            int removedFromDirFilesCount = 0;
            for (String fileName : fileNames) {
                File file = new File(dir.getPath(),fileName);
                if (file.delete()) {
                    controller.log.info("file " + fileName + " was successfully deleted");
                    removedFromDirFilesCount++;
                } else {
                    controller.log.info("can't remove file " + fileName);
                }
            }
            controller.log.info("was successfully removed " + removedFromDirFilesCount + " files");
        }
    }
}
