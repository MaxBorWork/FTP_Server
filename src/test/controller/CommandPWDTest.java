package controller;

import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandPWDTest {
    private CommandPWD pwd;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList257;

    @Before
    public void initTest() {
        pwd = new CommandPWD();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList257 = new ArrayList<>();
        inputList257.add("PWD");
        inputList257.add("PWD  ");
        inputList257.add("PWD  dfsdf");
    }

    @Test
    public void process() {
        for(String input: inputList257){
            assertEquals(new ReplyCode().getCODE_257(controller.getCurrentDir()),  pwd.process(input, code, controller));
        }
    }


}