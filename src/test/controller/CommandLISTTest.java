package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandLISTTest {
    private CommandLIST list;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList;

    @Before
    public void initTest() {
        list = new CommandLIST();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList = new ArrayList<>();
        Config.ROOT = "/home/pashkevich_alena/server";
        inputList.add("");

    }

    @Test
    public void process() {
        for(String input: inputList){
            assertEquals("",  list.process(input, code, controller));
        }
    }

}
