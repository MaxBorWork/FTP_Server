package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;

import java.io.File;
import java.io.PrintWriter;

public class CommandDELE implements CommandProcess {

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){

        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            File file = new File(Config.ROOT + File.pathSeparator + messageSplit[Config.FIRST_ARGUMENT_INDEX]);

            if (!file.isDirectory() && file.delete()) {
                return code.getCODE_250();
            } else {
                return code.getCODE_550();
            }
        } else {
            return code.getCODE_501();
        }
    }

}
