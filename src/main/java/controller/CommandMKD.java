package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;

public class CommandMKD implements CommandProcess {

    private Logger log = Logger.getLogger(CommandMKD.class);

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){

        String[] messageSplit = message.split(Config.SPACE);

        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String fullPath = messageSplit[Config.FIRST_ARGUMENT_INDEX];
            if (!fullPath.contains(Config.ROOT)) {
                fullPath = Config.ROOT + "/" + fullPath;
            }
            File file = new File(fullPath);
            if (file.mkdir()) {
                return code.getCODE_200();
            } else {
                return code.getCODE_550();
            }
        } else {
            return code.getCODE_501();
        }
    }
}
