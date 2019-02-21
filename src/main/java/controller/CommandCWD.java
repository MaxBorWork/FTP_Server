package controller;

import model.CommandProccess;

import model.ReplyCode;

import java.io.File;

public class CommandCWD implements CommandProccess {

    public String process(String message, CommandsController controller){
        String[] messageSplit = message.split(controller.SPACE);

        if (messageSplit.length == controller.SIZE_OF_COMMAND_WITH_ONE_ARGUMENT) {
            String fullDirPath = messageSplit[controller.FIRST_ARGUMENT_INDEX];
            if (!fullDirPath.contains(controller.ROOT)) {
                fullDirPath = controller.ROOT + "/" + messageSplit[controller.FIRST_ARGUMENT_INDEX];

            }
            File rootNew = new File(fullDirPath);

            if(rootNew.isDirectory() && rootNew.exists()){
//                dataSocket.createDataConnection(rootNew.getAbsolutePath(), null);
                return ReplyCode.CODE_250;

            } else return ReplyCode.CODE_550;

        } else return ReplyCode.CODE_501;
    }
}
