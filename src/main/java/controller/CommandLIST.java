package controller;

import model.*;
import java.io.PrintWriter;

public class CommandLIST implements CommandProcess {

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){

        String resp150 = code.getCODE_150(controller.getCurrentType(),
                                            controller.getCurrentDir(),
                                            DataTransferringController.pasvMessage());

        String[] messageSplit = message.split(Config.SPACE);
        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {
            writer.println(resp150);
            controller.getDataSocket().createDataConnection(controller.getCurrentDir(), Command.LIST, controller);
        } else {
            return code.getCODE_501();
        }
        return code.getCODE_226();
    }

}
