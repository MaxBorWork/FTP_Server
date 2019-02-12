package controller;

import model.ReplyCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandsControllerTest {

    private CommandsController controller;

    @Before
    public void setUp() throws Exception {
        controller = new CommandsController();
    }

    @After
    public void tearDown() throws Exception {
        controller = null;
    }

    @Test
    public void userCommand() {
        String message = "USER admin";
        String epectedResult = ReplyCode.CODE_331;
        assertEquals(epectedResult, controller.userCommand(message));
    }

    @Test
    public void userCommand1() {
        String message = "USER test";
        String epectedResult = ReplyCode.CODE_530;
        assertEquals(epectedResult, controller.userCommand(message));
    }

    @Test
    public void userCommand2() {
        String message = "Admin";
        String epectedResult = ReplyCode.CODE_500;
        assertEquals(epectedResult, controller.userCommand(message));
    }

    @Test
    public void passwordCommand() {
        String userMessage = "USER admin";
        controller.userCommand(userMessage);
        String passMessage = "PASS 123";
        String epectedResult = ReplyCode.CODE_230;
        assertEquals(epectedResult, controller.passwordCommand(passMessage));
    }

    @Test
    public void passwordCommand1() {
        String userMessage = "USER admin";
        controller.userCommand(userMessage);
        String message = "PASS qwer";
        String epectedResult = ReplyCode.CODE_530;
        assertEquals(epectedResult, controller.passwordCommand(message));
    }

    @Test
    public void passwordCommand2() {
        String userMessage = "USER admin";
        controller.userCommand(userMessage);
        String message = "123";
        String epectedResult = ReplyCode.CODE_500;
        assertEquals(epectedResult, controller.passwordCommand(message));
    }

    @Test
    public void removeDirectoryCommand() {
    }

    @Test
    public void deleteCommand() {
    }

    @Test
    public void makeDirectoryCommand() {

    }

    @Test
    public void listCommand() {
    }

    @Test
    public void portCommand() {
    }

    @Test
    public void storeCommand() {
    }

    @Test
    public void changeWorkDirCommand() {
    }

    @Test
    public void printWorkDirCommand() {
    }

    @Test
    public void retrieveCommand() {
    }

    @Test
    public void systCommand() {
    }


    @Test
    public void getType() {
    }

    @Test
    public void pasvResponse() {
    }
}