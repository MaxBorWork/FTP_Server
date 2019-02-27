package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandUSERTest {
    private CommandUSER user;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList530;
    private List<String> inputList331;
    private List<String> inputList500;

    @Before
    public void initTest() {
        user = new CommandUSER();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList530 = new ArrayList<>();
        inputList331 = new ArrayList<>();
        inputList500 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/server";

        inputList331.add("USER admin");
        inputList331.add("USER anonymous");

        inputList530.add("USER masha");
        inputList530.add("USER vasy");

        inputList500.add("USER admin hjgh");
        inputList500.add("USER");
        inputList500.add("");
    }

    @Test
    public void process() {
        for(String input: inputList530){
            assertEquals(CommandsController.reply.codeToMessage.get(530).toString(),  user.process(input, code, controller));
        }
        for(String input: inputList331){
            assertEquals(CommandsController.reply.codeToMessage.get(331).toString(),  user.process(input, code, controller));
        }
        for(String input: inputList500){
            assertEquals(CommandsController.reply.codeToMessage.get(500).toString(),  user.process(input, code, controller));
        }
    }

}