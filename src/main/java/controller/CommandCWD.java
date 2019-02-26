package controller;

import model.CommandProcess;

import model.Config;
import model.ReplyCode;

import java.io.File;
import java.io.PrintWriter;

public class CommandCWD implements CommandProcess {

    public String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);

        String fullDirPath = message.substring(message.indexOf(" ")+1);

        if (!fullDirPath.contains(Config.ROOT)) {
             fullDirPath = controller.getCurrentDir() + "/" + fullDirPath;
        }
        File rootNew = new File(fullDirPath);

        if(rootNew.isDirectory() && rootNew.exists()){
            controller.setCurrentDir(fullDirPath);
            return CommandsController.reply.codeToMessage.get(250).toString();

        } else return  CommandsController.reply.codeToMessage.get(550).toString();
    }
}
