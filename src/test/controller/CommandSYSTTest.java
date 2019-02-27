package controller;

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
    private List<String> inputList;

    @Before
    public void initTest() {
        syst = new CommandSYST();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList = new ArrayList<>();
        //   inputList.add();

    }

    @Test
    public void process() {
        for(String input: inputList){
            assertEquals("   ",  syst.process(input, code, controller));
        }
    }

}