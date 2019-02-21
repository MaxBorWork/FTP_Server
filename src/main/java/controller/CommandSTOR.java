package controller;

import model.Command;
import model.CommandProcess;
import model.Config;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.PrintWriter;


public class CommandSTOR implements CommandProcess {

    private Logger log = Logger.getLogger(CommandSTOR.class);

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(" ");
        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String filename = messageSplit[Config.FIRST_ARGUMENT_INDEX];
            if (!filename.contains("/")) {
                filename = Config.ROOT + "/" + filename;
            }
            writer.println(code.getCODE_150(controller.getCurrentType(),
                    controller.getCurrentDir(),
                    DataTransferringController.pasvMessage()));
            controller.getDataSocket().createDataConnection(filename, Command.STOR);
        } else return code.getCODE_501();
        return code.getCODE_226();
    }
}
