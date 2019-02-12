package controller;

import model.ReplyCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandsControllerTest {

    private CommandsController controller;
<<<<<<< HEAD
=======
    private String systCommandInput[] =  new String[]{"SYST","SYST system", "", "SYST sys system"};
    private String getTypeCommandInput[] = new String[]{"TYPE I","TYPE A", "TYPE I dd", "", "TYPE"};
>>>>>>> 4a3a16475eb256c848749bc9a3e7d560be836e1b

    @Before
    public void setUp() throws Exception {
        controller = new CommandsController();
    }

    @After
    public void tearDown() throws Exception {
<<<<<<< HEAD
        controller = null;
=======

>>>>>>> 4a3a16475eb256c848749bc9a3e7d560be836e1b
    }

    @Test
    public void userCommand() {
        String message = "USER admin";
<<<<<<< HEAD
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
=======

>>>>>>> 4a3a16475eb256c848749bc9a3e7d560be836e1b
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
<<<<<<< HEAD
    public void systCommand() {
    }


    @Test
    public void getType() {
=======
    public void userCommand1() {
    }

    @Test
    public void userCommand2() {
    }

    @Test
    public void passwordCommand1() {
    }

    @Test
    public void systCommandCode215() {
        String expectedResult = ReplyCode.CODE_215;
        for(String message: systCommandInput){
            assertEquals(expectedResult, controller.systCommand(message));
        }
    }

    @Test
    public void systCommandCode501() {
        String expectedResult = ReplyCode.CODE_501;
        for(String message: systCommandInput){
            assertEquals(expectedResult, controller.systCommand(message));
        }
    }

    @Test
    public void removeDirectoryCommand1() {
    }

    @Test
    public void deleteCommand1() {
    }

    @Test
    public void makeDirectoryCommand1() {
    }

    @Test
    public void listCommand1() {
    }

    @Test
    public void changeWorkDirCommand1() {
    }

    @Test
    public void printWorkDirCommandCode501() {
        String expectedResult = ReplyCode.CODE_501;
        for(String message: systCommandInput){
            assertEquals(expectedResult, controller.printWorkDirCommand(message));
        }

    }

    @Test
    public void printWorkDirCommandCode257() {
        String expectedResult = ReplyCode.CODE_257;
        for(String message: systCommandInput){
            assertEquals(expectedResult, controller.printWorkDirCommand(message));
        }
    }

    @Test
    public void retrieveCommand1() {
    }

    @Test
    public void storeCommand1() {
    }

    @Test
    public void portCommand1() {
    }

    @Test
    public void getTypeCode501() {
        for(String message: getTypeCommandInput){
            assertEquals( ReplyCode.CODE_501, controller.getType(message));
        }
    }

    @Test
    public void getTypeCode200() {
        for(String message: getTypeCommandInput){
            assertEquals( ReplyCode.CODE_200, controller.getType(message));
        }
>>>>>>> 4a3a16475eb256c848749bc9a3e7d560be836e1b
    }

    @Test
    public void pasvResponse() {
    }
}