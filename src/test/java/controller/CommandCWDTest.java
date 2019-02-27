package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandCWDTest {
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
        Config.ROOT = "/home/pashkevich_alena/server";
        inputList.add("CWD 1.bmp");
        inputList.add("CWD NotExistCatalog");
        inputList.add("CWD литература");
        inputList.add("CWD Новый каталог");
        inputList.add("CWD Lab1");
    }

    @Test
    public void process() {
        System.out.println("assert code 250");
        for(String input: inputList){
            assertEquals(controller.reply.codeToMessage.get(250).toString(),  cdup.process(input, code, controller));
        }

        System.out.println("assert code 550");
        for(String input: inputList){
            assertEquals(controller.reply.codeToMessage.get(550).toString(),  cdup.process(input, code, controller));
        }
    }

}
