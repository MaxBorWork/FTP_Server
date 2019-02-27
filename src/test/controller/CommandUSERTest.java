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
    private List<String> inputList;

    @Before
    public void initTest() {
        user = new CommandUSER();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList = new ArrayList<>();
        Config.ROOT = "/home/pashkevich_alena/server";
        inputList.add("USER admin");

    }

    @Test
    public void process() {
        for(String input: inputList){
            assertEquals(CommandsController.reply.codeToMessage.get(331).toString(),  user.process(input, code, controller));
        }
    }

}