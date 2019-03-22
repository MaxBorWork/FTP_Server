package controller;

import model.*;

import java.io.File;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

public class CommandRETR implements CommandProcess {

    public String process(String message, ReplyCode code, CommandsController controller) {

        String filename = message.substring(message.indexOf(" ") + 1);
        if (!filename.contains("/")) {
            filename = controller.getCurrentDir() + "/" + filename;
        }

        String programRight = (new com.sun.security.auth.module.UnixSystem()).getUsername();

        try {
            Path path = Paths.get(filename);
            FileOwnerAttributeView ownerView = Files.getFileAttributeView(
                    path, FileOwnerAttributeView.class);
            UserPrincipal owner = ownerView.getOwner();
            String ownerName = owner.toString();

            if(programRight.equals("root")){
                controller.getWriter().println(code.getCODE_150(controller.getCurrentType(),
                        controller.getCurrentDir(),
                        DataTransferringController.pasvMessage()));

                controller.getDataSocket().createDataConnection(filename, "RETR", controller);
                return CommandsController.reply.codeToMessage.get(226).toString();

            } else {
                if(programRight.equals(ownerName)){
                    controller.getWriter().println(code.getCODE_150(controller.getCurrentType(),
                            controller.getCurrentDir(),
                            DataTransferringController.pasvMessage()));

                    controller.getDataSocket().createDataConnection(filename, "RETR", controller);
                    return CommandsController.reply.codeToMessage.get(226).toString();
                } else {
                    return CommandsController.reply.codeToMessage.get(550).toString();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return CommandsController.reply.codeToMessage.get(550).toString();
        }
    }
}
