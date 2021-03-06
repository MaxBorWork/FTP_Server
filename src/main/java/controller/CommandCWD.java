package controller;

import model.CommandProcess;

import model.Config;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.ServerSocketAccept;

import java.io.File;
import java.io.PrintWriter;

public class CommandCWD implements CommandProcess {

    private Logger log = Logger.getLogger(CommandCWD.class);

    public String process(String message,  ReplyCode code, CommandsController controller){
        controller.loggerConfig(log);
        String fullDirPath = message.substring(message.indexOf(" ")+1);

        if (!fullDirPath.contains(Config.ROOT)) {
             fullDirPath = controller.getCurrentDir() + "/" + fullDirPath;
        }

        File rootNew = new File(fullDirPath);

        if(rootNew.isDirectory() && rootNew.exists()){
            controller.setCurrentDir(fullDirPath);
            log.debug("current work dir is " + fullDirPath);
            return CommandsController.reply.codeToMessage.get(200).toString();
        } else {
            return  CommandsController.reply.codeToMessage.get(551).toString();
        }
    }
}
