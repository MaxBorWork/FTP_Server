package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandMKDTest {
    private CommandMKD mkd;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList200;
    private List<String> inputList550;


    @Before
    public void initTest() {
        mkd = new CommandMKD();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList200 = new ArrayList<>();
        inputList550 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/server";
        controller.setCurrentDir(Config.ROOT );


        inputList550.add("MKD Безымянный документ");
        inputList550.add("MKD ");
        inputList200.add("MKD Безымянный документ");

    }

    @Test
    public void process() {
        for(String input: inputList200){
            assertEquals(controller.reply.codeToMessage.get(200).toString(),  mkd.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(controller.reply.codeToMessage.get(550).toString(),  mkd.process(input, code, controller));
        }
    }

}
