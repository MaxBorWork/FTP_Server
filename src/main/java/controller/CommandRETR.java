package controller;

import model.*;

import java.io.PrintWriter;

public class CommandRETR implements CommandProcess {

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller) {
        String[] messageSplit = message.split(" ");
      //  if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
           // String filename = messageSplit[Config.FIRST_ARGUMENT_INDEX];
        String filename = message.substring(message.indexOf(" ")+1);
            if (!filename.contains("/")) {
                filename = controller.getCurrentDir() + "/" + filename;
            }
            writer.println(code.getCODE_150(controller.getCurrentType(),
                                            controller.getCurrentDir(),
                                            DataTransferringController.pasvMessage()));
            controller.getDataSocket().createDataConnection(filename, "RETR", controller);
      //  } else return code.getCODE_501();
        return  controller.reply.codeToMessage.get(226).toString();
    }
}
