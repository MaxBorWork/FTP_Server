package controller;

import model.CommandProcess;

import model.Config;
import model.ReplyCode;

import java.io.File;
import java.io.PrintWriter;

public class CommandCWD implements CommandProcess {

    public String process(String message,  ReplyCode code, CommandsController controller){
        String[] messageSplit = message.split(Config.SPACE);

      //  if (messageSplit.length == Config.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
           // String fullDirPath = messageSplit[Config.FIRST_ARGUMENT_INDEX];
        String fullDirPath = message.substring(message.indexOf(" ")+1);
      //  fullDirPath.substring(1);

            if (!fullDirPath.contains(Config.ROOT)) {
               // fullDirPath = controller.getCurrentDir() + "/" + messageSplit[Config.FIRST_ARGUMENT_INDEX];
                 fullDirPath = controller.getCurrentDir() + "/" + fullDirPath;
            }

            File rootNew = new File(fullDirPath);

            if(rootNew.isDirectory() && rootNew.exists()){

                controller.setCurrentDir(fullDirPath);
                return controller.reply.codeToMessage.get(250).toString();

            } else return  controller.reply.codeToMessage.get(550).toString();

     //   } else return code.getCODE_501();
    }
}
