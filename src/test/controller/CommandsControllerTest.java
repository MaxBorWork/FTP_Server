package controller;

import model.Config;
import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;
//
//public class CommandsControllerTest {
//    private CommandsController controller = new CommandsController();;
//    private ReplyCode code;
//    private PrintWriter writer;
//
//    @Before
//    public void setUp() throws Exception {
//        new Config();
//        code = new ReplyCode();
//
//        Config.ROOT = "/etc/ftRoot/dir1";
//        controller.setCurrentDir(Config.ROOT);
//
//        writer = new PrintWriter(System.out);
//    }
//
//    @Test
//    public void getCommand() {
//        String line = "CDUP";
//        assertFalse(controller.getCommand(line, writer, code));
//    }
//
//    @Test
//    public void getCommand1() {
//        String line = "";
//        assertTrue(controller.getCommand(line, writer, code));
//    }
//
////    @Test
////    public void getCommand2() {
////        String line = "FEAT";
////        assertFalse(controller.getCommand(line, writer, code));
////    }
//}

public class CommandsControllerTest {
    private CommandsController controller ;//= new CommandsController();;
    private ReplyCode code;
    private PrintWriter writer;

    @Before
    public void setUp() throws Exception {
        new Config();
        code = new ReplyCode();
        controller = new CommandsController("CDUP");

        Config.ROOT = "/etc/ftRoot/dir1";
        controller.setCurrentDir(Config.ROOT);

        writer = new PrintWriter(System.out);
    }

    @Test
    public void getCommand() {
        String line = "CDUP";
        assertFalse(controller.getCommand(line, writer, code));

        line = "";
        assertTrue(controller.getCommand(line, writer, code));

         line = "FEAT";
        assertFalse(controller.getCommand(line, writer, code));
    }
}