package controller;

import model.*;

public class CommandRETR implements CommandProcess {

    public String process(String message,  ReplyCode code, CommandsController controller) {

        String filename = message.substring(message.indexOf(" ")+1);
            if (!filename.contains("/")) {
                filename = controller.getCurrentDir() + "/" + filename;
            }

            controller.getWriter().println(code.getCODE_150(controller.getCurrentType(),
                                            controller.getCurrentDir(),
                                            DataTransferringController.pasvMessage()));

            controller.getDataSocket().createDataConnection(filename, "RETR", controller);

        return  CommandsController.reply.codeToMessage.get(226).toString();
    }
}
