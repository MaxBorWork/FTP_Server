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
    private List<String> inputList;

    @Before
    public void initTest() {
        cdup = new CommandCDUP();
        code = new ReplyCode();
        controller = new CommandsController();
        inputList = new ArrayList<>();
     //   Config.ROOT = "/home/pashkevich_alena/server";

      /*  inputList.add();
        inputList.add();
        inputList.add();
        inputList.add();
        inputList.add();
        inputList.add();
        inputList.add();*/
    }

    @Test
    public void process() {
        for(String input: inputList){
            assertEquals(controller.reply.codeToMessage.get(250).toString(),  cdup.process(input, code, controller));
        }

        for(String input: inputList){
            assertEquals(controller.reply.codeToMessage.get(550).toString(),  cdup.process(input, code, controller));
        }

        for(String input: inputList){
            assertEquals(controller.reply.codeToMessage.get(501).toString(),  cdup.process(input, code, controller));
        }
        }

    }

