package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandSYSTTest {
    private CommandSYST syst;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList215;
    private List<String> inputList500;

    @Before
    public void initTest() {
        syst = new CommandSYST();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList215 = new ArrayList<>();
        inputList500 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/server";
        controller.setCurrentDir(Config.ROOT );


        inputList215.add("SYST");
        inputList215.add("SYST ");

        inputList500.add("SYST df");
        inputList500.add("SYST dg fg");
    }

    @Test
    public void process() {
        for(String input: inputList215){
            assertEquals(controller.reply.codeToMessage.get(215).toString(),  syst.process(input, code, controller));
        }

        for(String input: inputList500){
            assertEquals(controller.reply.codeToMessage.get(500).toString(),  syst.process(input, code, controller));
        }
    }

}