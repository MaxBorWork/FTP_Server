package controller;

import model.*;
import java.io.PrintWriter;

public class CommandLIST implements CommandProcess {

    public String process(String message,  ReplyCode code, CommandsController controller){

        String resp150 = code.getCODE_150(controller.getCurrentType(),
                                            controller.getCurrentDir(),
                                            DataTransferringController.pasvMessage());

        String[] messageSplit = message.split(Config.SPACE);
        if (messageSplit.length == Config.SIZE_OF_COMMAND_WITHOUT_ARGUMENT) {

         //убрать  writer отсюда
      //      writer.println(resp150);

            controller.getDataSocket().createDataConnection(controller.getCurrentDir(), "LIST", controller);
 //           return   resp150;
        } else {
            return  controller.reply.codeToMessage.get(501).toString();
        }
        return  controller.reply.codeToMessage.get(226).toString();
    }

}
