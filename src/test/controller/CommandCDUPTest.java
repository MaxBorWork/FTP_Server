package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandCDUPTest {
    private CommandCDUP cdup;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList250;
    private List<String> inputList550;
    private List<String> inputList501;

    @Before
    public void initTest() {
        new Config();
        cdup = new CommandCDUP();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList250 = new ArrayList<>();
        inputList550 = new ArrayList<>();
        inputList501 = new ArrayList<>();



        inputList250.add("CDUP");
        inputList550.add("CDUP");
        inputList501.add("CDUP dir1");
    }

    @Test
    public void process() {
        for(String input: inputList250) {
            controller.setCurrentDir(Config.ROOT + "/dir1");
            assertEquals(CommandsController.reply.codeToMessage.get(250).toString(), cdup.process(input, code, controller));
        }

        for(String input: inputList550) {
            controller.setCurrentDir(Config.ROOT + "/dir1/dirr");
            assertEquals(CommandsController.reply.codeToMessage.get(550).toString(), cdup.process(input, code, controller));
        }

        for(String input: inputList501) {
            controller.setCurrentDir(Config.ROOT + "/dir1");
            assertEquals(CommandsController.reply.codeToMessage.get(501).toString(), cdup.process(input, code, controller));
        }
    }

}

