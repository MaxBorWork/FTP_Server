package controller;

import model.CommandProcess;

import model.Config;
import model.ReplyCode;

import java.io.File;
import java.io.PrintWriter;

public class CommandCWD implements CommandProcess {

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String fullDirPath = messageSplit[Config.FIRST_ARGUMENT_INDEX];
            if (!fullDirPath.contains(Config.ROOT)) {
                fullDirPath = controller.getCurrentDir() + "/" + messageSplit[Config.FIRST_ARGUMENT_INDEX];

            }
            File rootNew = new File(fullDirPath);

            if(rootNew.isDirectory() && rootNew.exists()){
                controller.setCurrentDir(fullDirPath);
                return code.getCODE_250();

            } else return code.getCODE_550();

        } else return code.getCODE_501();
    }
}
