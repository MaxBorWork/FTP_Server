package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandPASVTest {
    private CommandPASV pasv;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList227;
    private List<String> inputList500;

    @Before
    public void initTest() {
        pasv = new CommandPASV();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList227 = new ArrayList<>();
        inputList500 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/server";
        controller.setCurrentDir(Config.ROOT );

        inputList227.add("PASV");
        inputList227.add("PASV ");

        inputList500.add("PASV  lll lll");
        inputList500.add("PASV fdgdfg");

    }

    @Test
    public void process() {
        for(String input: inputList227){
            assertEquals( code.getCODE_227(), pasv.process(input, code, controller));
        }

        for(String input: inputList500){
            assertEquals(controller.reply.codeToMessage.get(500).toString(),  pasv.process(input, code, controller));
        }
    }

}