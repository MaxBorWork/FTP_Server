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
    private String systCommandInput[] =  new String[]{"SYST","SYST system", "", "SYST sys system"};
    private String getTypeCommandInput[] = new String[]{"TYPE I","TYPE A", "TYPE I dd", "", "TYPE"};

    @Before
    public void setUp() throws Exception {
        controller = new CommandsController();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void userCommand() {
        String message = "USER admin";

    }

    @Test
    public void passwordCommand() {
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
    }

    @Test
    public void pasvResponse() {
    }
}