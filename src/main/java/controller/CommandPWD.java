package controller;

import model.CommandProcess;
import model.Config;
import model.LogMessages;
import model.ReplyCode;
import org.apache.log4j.Logger;
import util.ServerSocketAccept;

import java.io.PrintWriter;

public class CommandPWD implements CommandProcess {

    private Logger log = Logger.getLogger(CommandPWD.class);

    public String process(String path, ReplyCode code, CommandsController controller) {
        controller.loggerConfig(log);
        log.debug("current directory is " + controller.getCurrentDir());
        return new ReplyCode().getCODE_257(controller.getCurrentDir());
    }
}
