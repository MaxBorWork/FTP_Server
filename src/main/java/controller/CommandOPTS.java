package controller;

import model.CommandProcess;
import model.ReplyCode;

public class CommandOPTS implements CommandProcess {
    @Override
    public String process(String message, ReplyCode code, CommandsController controller) {
        return CommandsController.reply.codeToMessage.get(200).toString();
    }
}
