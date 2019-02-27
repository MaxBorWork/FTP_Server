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
    private CommandsController controller;
    private List<String> inputList;

    @Before
    public void initTest() {
        dele = new CommandDELE();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList = new ArrayList<>();
        Config.ROOT = "/home/pashkevich_alena/server";
        inputList.add("");

    }

    @Test
    public void process() {
        for(String input: inputList){
            assertEquals("",  dele.process(input, code, controller));
        }
    }
}