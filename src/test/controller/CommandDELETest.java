package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandDELETest {
    private CommandMKD mkd;
    private CommandDELE dele;
    private ReplyCode code;
    private CommandsController controller= new CommandsController("DELE");
    private List<String> inputList250;
    private List<String> inputList550;

    @Before
    public void initTest() {
        new Config();
        mkd = new CommandMKD();
        dele = new CommandDELE();
        code = new ReplyCode();
        inputList250 = new ArrayList<>();
        inputList550 = new ArrayList<>();

        Config.ROOT = "/home/pashkevich_alena/s";
        controller.setCurrentDir(Config.ROOT );


        inputList550.add("DELE 2.bmp");
        inputList550.add("DELE Безымянный документ1");

        inputList250.add("DELE me.jpg");
        inputList250.add("DELE x.txt");


        if(!(new File( Config.ROOT +"/me.jpg").exists())){
            try{
                new File(Config.ROOT +"/me.jpg").createNewFile();
            } catch(Exception e){
                e.printStackTrace();
            }

        }

        if(!(new File( Config.ROOT +"/x.txt").exists())){
            try{ new File(Config.ROOT +"/x.txt").createNewFile();

            } catch(Exception e){
                e.printStackTrace();
            }

        }
        //for(String input: inputList250){
           // mkd.process(input, code, controller);
          //   }
    }

    @Test
    public void process() {
        for(String input: inputList250){
            assertEquals(controller.reply.codeToMessage.get(200).toString(),  dele.process(input, code, controller));
        }

        for(String input: inputList550){
            assertEquals(controller.reply.codeToMessage.get(551).toString(),  dele.process(input, code, controller));
        }

//        try {
//            controller.getDataSocket().getServerSocket().close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}