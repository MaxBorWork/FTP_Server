package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandDELETest {
    private CommandDELE dele;
    private ReplyCode code;
    private CommandsController controller= new CommandsController();
    private List<String> inputList250;
    private List<String> inputList550;

    @Before
    public void initTest() {
        new Config();
        dele = new CommandDELE();
        code = new ReplyCode();
        inputList250 = new ArrayList<>();
        inputList550 = new ArrayList<>();

        Config.ROOT = "/etc/ftRoot";
        controller.setCurrentDir(Config.ROOT );


        inputList550.add("DELE 2.bmp");
        inputList550.add("DELE Безымянный документ1");
        inputList250.add("DELE me.jpg");
        inputList250.add("DELE x.txt");
    }

    @Test
    public void process() {
        for(String input: inputList250){
            assertEquals(controller.reply.codeToMessage.get(200).toString(),  dele.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(controller.reply.codeToMessage.get(550).toString(),  dele.process(input, code, controller));
        }
    }
}