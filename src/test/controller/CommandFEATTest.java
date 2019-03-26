package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandFEATTest {

    private CommandFEAT feat;
    private CommandsController controller = new CommandsController("RMD");
    private List<String> inputList211;
    private ReplyCode code;

    @Before
    public void initTest() {
        new Config();
        feat = new CommandFEAT();

        code = new ReplyCode();
        inputList211 = new ArrayList<>();
        Config.ROOT = "/home/pashkevich_alena/s";
        controller.setCurrentDir(Config.ROOT);

        inputList211.add("RMD 2.bmp");
        inputList211.add("RMD Безымянный документ1");
    }

    @Test
    public void process() {
        for(String input: inputList211) {
            assertEquals(CommandsController.reply.codeToMessage.get(211).toString(), feat.process(input, code, controller));
        }
    }
}