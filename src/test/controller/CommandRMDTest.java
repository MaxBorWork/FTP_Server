package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandRMDTest {
    private CommandRMD rmd;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList250;
    private List<String> inputList550;

    @Before
    public void initTest() {
        rmd = new CommandRMD();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList250 = new ArrayList<>();
        inputList550 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/server";
        controller.setCurrentDir(Config.ROOT );


        inputList550.add("RMD 2.bmp");
        inputList550.add("RMD Безымянный документ1");

        inputList250.add("RMD 1.bmp");
        inputList250.add("RMD Безымянный документ");

    }

    @Test
    public void process() {
        for(String input: inputList250){
            assertEquals(controller.reply.codeToMessage.get(250).toString(),  rmd.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(controller.reply.codeToMessage.get(550).toString(),  rmd.process(input, code, controller));
        }
    }


}