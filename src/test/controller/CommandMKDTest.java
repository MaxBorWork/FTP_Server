package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandMKDTest {
    private CommandDELE dele;
    private CommandMKD mkd;
    private ReplyCode code;
    private CommandsController controller = new CommandsController();
    private List<String> inputList200;
    private List<String> inputList550;

    @Before
    public void initTest() {
        new Config();
        dele = new CommandDELE();
        mkd = new CommandMKD();
        code = new ReplyCode();
        inputList200 = new ArrayList<>();
        inputList550 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/s";
        controller.setCurrentDir(Config.ROOT );

        inputList550.add("MKD Безымянный документ");
        inputList550.add("MKD ");
        inputList200.add("MKD testIT");
       if(new File(Config.ROOT+"/testIT").exists()){
new File(Config.ROOT+"/testIT").delete();
       }

    }

    @Test
    public void process() {
        for(String input: inputList200){
            assertEquals(CommandsController.reply.codeToMessage.get(200).toString(),  mkd.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(CommandsController.reply.codeToMessage.get(551).toString(),  mkd.process(input, code, controller));
        }
    }

}
