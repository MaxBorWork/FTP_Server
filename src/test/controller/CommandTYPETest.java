package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class CommandTYPETest {
    private ReplyCode code;
    private CommandsController controller;
    private List<String> inputList200;
    private List<String> inputList501;
    private CommandTYPE type;

    @Before
    public void initTest() {
        new Config();
        type = new CommandTYPE();
        code = new ReplyCode();
        controller = new CommandsController("TYPE");
        inputList501 = new ArrayList<>();
        inputList200 = new ArrayList<>();

    Config.ROOT = "/home/pashkevich_alena/server";

        inputList200.add("TYPE I");
        inputList200.add("TYPE A");

        inputList501.add("TYPE");
        inputList501.add("TYPE D dfd");
        inputList501.add("TYPE C");
}

    @Test
    public void process() {
        for(String input: inputList200){
            Assert.assertEquals(CommandsController.reply.codeToMessage.get(200).toString(),  type.process(input, code, controller));
        }
        for(String input: inputList501){
            Assert.assertEquals(CommandsController.reply.codeToMessage.get(501).toString(),  type.process(input, code, controller));
        }

//        try {
//            controller.getDataSocket().getServerSocket().close();
//        } catch (IOException | NullPointerException e) {
//            e.printStackTrace();
//            e.printStackTrace();
//        }
    }

}