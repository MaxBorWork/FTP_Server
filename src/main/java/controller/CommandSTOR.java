package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

public class CommandSTOR implements CommandProcess {

    private Logger log = Logger.getLogger(CommandSTOR.class);

    public String process(String message, ReplyCode code, CommandsController controller){

        String filename = message.substring(message.indexOf(" ")+1);
        if (!filename.contains("/")) {
            filename = controller.getCurrentDir() + "/" + filename;
        }


        String programRight = (new com.sun.security.auth.module.UnixSystem()).getUsername();

        try {
            Path path = Paths.get(controller.getCurrentDir());
            FileOwnerAttributeView ownerView = Files.getFileAttributeView(
                    path, FileOwnerAttributeView.class);
            UserPrincipal owner = ownerView.getOwner();
            String ownerName = owner.toString();

            if (programRight.equals("root")){
                controller.getWriter().println(code.getCODE_150(controller.getCurrentType(),
                        controller.getCurrentDir(),
                        DataTransferringController.pasvMessage()));

                controller.getDataSocket().createDataConnection(filename, "STOR", controller);

                return CommandsController.reply.codeToMessage.get(226).toString();
            } else {
                if(!programRight.equals(ownerName)){
                    return CommandsController.reply.codeToMessage.get(553).toString();

                } else {
                    controller.getWriter().println(code.getCODE_150(controller.getCurrentType(),
                            controller.getCurrentDir(),
                            DataTransferringController.pasvMessage()));

                    controller.getDataSocket().createDataConnection(filename, "STOR", controller);

                    return CommandsController.reply.codeToMessage.get(226).toString();
                }

            }

        } catch(Exception e){
            e.printStackTrace();
            return CommandsController.reply.codeToMessage.get(550).toString();

        }

/*
controller.getWriter().println(code.getCODE_150(controller.getCurrentType(),
controller.getCurrentDir(),
DataTransferringController.pasvMessage()));

controller.getDataSocket().createDataConnection(filename, "STOR", controller);

return CommandsController.reply.codeToMessage.get(226).toString();*/

    }
}
