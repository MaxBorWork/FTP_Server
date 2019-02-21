package controller;

import model.*;

import java.io.PrintWriter;

import static model.Config.ROOT;


public class CommandRETR implements CommandProcess {

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller) {
        String[] messageSplit = message.split(" ");
        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String filename = messageSplit[Config.FIRST_ARGUMENT_INDEX];
            if (!filename.contains("/")) {
                filename = controller.getCurrentDir() + "/" + messageSplit[Config.FIRST_ARGUMENT_INDEX];
            }
            writer.println(code.getCODE_150(controller.getCurrentType(),
                                            controller.getCurrentDir(),
                                            DataTransferringController.pasvMessage()));
            controller.getDataSocket().createDataConnection(filename, Command.RETR);
        } else return code.getCODE_501();
        return code.getCODE_226();
    }
}
