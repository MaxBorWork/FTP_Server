package controller;


import model.CommandProcess;
import model.ReplyCode;
import org.apache.log4j.Logger;

import java.io.PrintWriter;

public class CommandPWD implements CommandProcess {

    private Logger log = Logger.getLogger(CommandPWD.class);

    public String process(String path, ReplyCode code, CommandsController controller) {
        log.info("current directory is " + controller.getCurrentDir());
        return new ReplyCode().getCODE_257(controller.getCurrentDir());
    }
}


