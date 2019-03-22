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

            if (!fileName.contains(Config.ROOT)) fileName = controller.getCurrentDir() + "/" + fileName;


            File file = new File(fileName);

            if (!file.isDirectory() && file.delete()) {
                return  CommandsController.reply.codeToMessage.get(200).toString();
            } else return  CommandsController.reply.codeToMessage.get(551).toString();


    }

}
