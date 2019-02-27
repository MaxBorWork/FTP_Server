package controller;

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
    private List<String> inputList;

    @Before
    public void initTest() {
        pasv = new CommandPASV();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList = new ArrayList<>();
       // inputList.add();

    }

    @Test
    public void process() {
        for(String input: inputList){
            assertEquals("   ",  pasv.process(input, code, controller));
        }
    }


}