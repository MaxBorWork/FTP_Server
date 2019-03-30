package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandRMDTest {
    private CommandRMD rmd;
    private CommandMKD cwd;
    private ReplyCode code;
    private CommandsController controller = new CommandsController("RMD");
    private List<String> inputList250;
    private List<String> inputList550;

    @Before
    public void initTest() {
        new Config();
        rmd = new CommandRMD();
        cwd = new CommandMKD();
        code = new ReplyCode();
        inputList250 = new ArrayList<>();
        inputList550 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/s";
        controller.setCurrentDir(Config.ROOT);

        inputList550.add("RMD 2.bmp");
        inputList550.add("RMD Безымянный документ1");

        inputList250.add("RMD d2");
        inputList250.add("RMD testedIT");


        for(String input: inputList250){
            cwd.process(input, code, controller);
        }

    }

    @Test
    public void process() {
        for(String input: inputList250){
            assertEquals(CommandsController.reply.codeToMessage.get(250).toString(),  rmd.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(CommandsController.reply.codeToMessage.get(551).toString(),  rmd.process(input, code, controller));
        }

//        try {
//            controller.getDataSocket().getServerSocket().close();
//        } catch (IOException | NullPointerException e) {
//            e.printStackTrace();
//            e.printStackTrace();
//        }
    }
}