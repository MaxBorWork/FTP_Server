package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;

import java.io.File;
import java.io.PrintWriter;

public class CommandCDUP implements CommandProcess {

    @Override
    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller) {
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            String currDirPath = controller.getCurrentDir();
            String updirPath = Config.ROOT;
            String[] currDirPathSplit = currDirPath.split("/");

            if (currDirPathSplit.length > 1) {
                String trimString = "/" + currDirPathSplit[currDirPathSplit.length-1];
                updirPath = currDirPath.replace(trimString, "");
            }
            File rootNew = new File(updirPath);

            if(rootNew.isDirectory() && rootNew.exists()){
                controller.setCurrentDir(updirPath);
                return code.getCODE_250();

            } else return code.getCODE_550();

        } else return code.getCODE_501();
    }
}
