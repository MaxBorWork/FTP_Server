package controller;

import model.CommandProcess;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.File;

public class CommandSTOR implements CommandProcess {

    private Logger log = Logger.getLogger(CommandSTOR.class);

    public String process(String message,  ReplyCode code, CommandsController controller){

        String filename = message.substring(message.indexOf(" ")+1);
        if (!filename.contains("/")) {
            filename = controller.getCurrentDir() + "/" + filename;
        }

        if( !new File(filename).canWrite()){
            return  CommandsController.reply.codeToMessage.get(550).toString();
        }

        controller.getWriter().println(code.getCODE_150(controller.getCurrentType(),
               controller.getCurrentDir(),
                DataTransferringController.pasvMessage()));

        controller.getDataSocket().createDataConnection(filename, "STOR", controller);

        return  CommandsController.reply.codeToMessage.get(226).toString();
    }
}
