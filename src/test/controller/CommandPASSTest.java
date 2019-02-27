package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandPASSTest {
    private CommandPASS pass;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList530;
    private List<String> inputList500;
    private List<String> inputList230;

    @Before
    public void initTest() {
        pass= new CommandPASS();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList530 = new ArrayList<>();
        inputList500 = new ArrayList<>();
        inputList230 = new ArrayList<>();
        controller.setUsername("admin");
        Config.ROOT = "/home/pashkevich_alena/server";

        inputList500.add("PASS asd asd asd");
        inputList500.add("PASS");

        inputList530.add("PASS 345");
        inputList530.add("PASS gfdhghd");

        inputList230.add("PASS 123");

    }

    @Test
    public void process() {
        for(String input: inputList500){
            assertEquals(controller.reply.codeToMessage.get(500).toString(), pass.process(input, code, controller));
        }

        for(String input: inputList530){
            assertEquals(controller.reply.codeToMessage.get(530).toString(), pass.process(input, code, controller));
        }

        for(String input: inputList230){
            assertEquals(controller.reply.codeToMessage.get(230).toString(), pass.process(input, code, controller));
        }
    }

}
