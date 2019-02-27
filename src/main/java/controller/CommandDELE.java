package controller;

import model.CommandProcess;
import model.Config;
import model.ReplyCode;

import java.io.File;
import java.io.PrintWriter;

public class CommandDELE implements CommandProcess {

    public String process(String message,  ReplyCode code, CommandsController controller){

        String[] messageSplit = message.split(Config.SPACE);


            String fileName = message.substring(message.indexOf(" ")+1);
            if (!fileName.contains(Config.ROOT)) {
                fileName = controller.getCurrentDir() + "/" + fileName;
            }

            File file = new File(fileName);

            if (!file.isDirectory() && file.delete()) {
                return  controller.reply.codeToMessage.get(250).toString();
            } else return  controller.reply.codeToMessage.get(550).toString();


    }

}
