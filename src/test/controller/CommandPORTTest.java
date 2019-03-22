package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandPORTTest {
    private CommandPORT commandPORT;
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList200;
    private List<String> inputList501;

    @Before
    public void setUp() throws Exception {
        new Config();
        commandPORT = new CommandPORT();
        code = new ReplyCode();
        controller = new CommandsController();

        inputList501 = new ArrayList<>();
        inputList200 = new ArrayList<>();

        Config.ROOT = "/etc/ftRoot";

        inputList501.add("PORT ");
        inputList501.add("PORT (192,168,254,253,207,56) (192,168,254,253,207,56)");
        inputList200.add("PORT " + DataTransferringController.pasvMessage());
        Config.PORT_20_INT = 1488;
        inputList200.add("PORT " + DataTransferringController.pasvMessage());
    }

    @Test
    public void process() {
        for(String input: inputList200){
            controller.setCurrentDir(Config.ROOT);
            assertEquals(CommandsController.reply.codeToMessage.get(200).toString(),  commandPORT.process(input, code, controller));
        }

        for(String input: inputList501){
            assertEquals(CommandsController.reply.codeToMessage.get(501).toString(),  commandPORT.process(input, code, controller));
        }
    }
}