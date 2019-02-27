package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandCWDTest {
    private CommandCWD cwd;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList250;
    private List<String> inputList550;

    @Before
    public void initTest() {
        cwd = new CommandCWD();
        code = new ReplyCode();
        controller = new CommandsController();

        inputList550 = new ArrayList<>();
        inputList250 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/server";

        inputList550.add("CWD 1.bmp");
        inputList550.add("CWD NotExistCatalog");
        inputList250.add("CWD литература");
        inputList250.add("CWD New catalog");
        inputList250.add("CWD Lab1");
    }

    @Test
    public void process() {
        for(String input: inputList250){
            controller.setCurrentDir(Config.ROOT );
            assertEquals(controller.reply.codeToMessage.get(250).toString(),  cwd.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(controller.reply.codeToMessage.get(550).toString(),  cwd.process(input, code, controller));
        }
    }

}
