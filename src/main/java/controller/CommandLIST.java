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
            controller.getDataSocket().createDataConnection(controller.getCurrentDir(), "LIST", controller);
        } else {
            return  controller.reply.codeToMessage.get(501).toString();
        }
        return  controller.reply.codeToMessage.get(226).toString();
    }

}
