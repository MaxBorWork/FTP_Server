package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandLISTTest {
    private CommandLIST list;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList226;
    private List<String> inputList501;

    @Before
    public void setUp() throws Exception {
        new Config();
        list = new CommandLIST();
        code = new ReplyCode();
        controller = new CommandsController();

        inputList501 = new ArrayList<>();
        inputList226 = new ArrayList<>();

        Config.ROOT = "/etc/ftRoot";
        controller.setCurrentDir(Config.ROOT);

        controller.setWriter(new PrintWriter(System.out));

        inputList501.add("LIST 1.bmp");
        inputList501.add("LIST NotExistCatalog");
        inputList226.add("LIST");
    }

    @Test
    public void process() {
//        for(String input: inputList226){
//            controller.setCurrentDir(Config.ROOT);
//            assertEquals(CommandsController.reply.codeToMessage.get(226).toString(),  list.process(input, code, controller));
//        }
//
//        for(String input: inputList501){
//            assertEquals(CommandsController.reply.codeToMessage.get(501).toString(),  list.process(input, code, controller));
//        }
    }
}
