package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandCWDTest {
    private CommandCWD cwd;
    private CommandMKD mkd;
    private ReplyCode code;
    private CommandsController controller = new CommandsController("CWD");
    private List<String> inputList250;
    private List<String> inputList550;

    @Before
    public void initTest() {
        new Config();
        mkd = new CommandMKD();
        cwd = new CommandCWD();
        code = new ReplyCode();

        inputList550 = new ArrayList<>();
        inputList250 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/s";

        inputList550.add("CWD 1.bmp");
        inputList550.add("CWD NotExistCatalog");

        inputList250.add("CWD литература");
        inputList250.add("CWD New catalog");
        inputList250.add("CWD Lab1");

        for(String input: inputList250){
            controller.setCurrentDir(Config.ROOT);
           mkd.process(input, code, controller);
        }
    }

    @Test
    public void process() {
        for(String input: inputList250){
            controller.setCurrentDir(Config.ROOT);
            assertEquals(CommandsController.reply.codeToMessage.get(200).toString(),  cwd.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(CommandsController.reply.codeToMessage.get(551).toString(),  cwd.process(input, code, controller));
        }

//        try {
//            controller.getDataSocket().getServerSocket().close();
//        } catch (IOException | NullPointerException e) {
//            e.printStackTrace();
//            e.printStackTrace();
//        }
    }

}
