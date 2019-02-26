package controller;

import model.ReplyCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandUSERTest {

    private CommandUSER commandUSER;

    @Before
    public void setUp() throws Exception {
        commandUSER = new CommandUSER();
    }

    @Test
    public void process() {
        String message = "USER admin";
        String expectedResult = new ReplyCode().getCODE_331();
        assertEquals(expectedResult, commandUSER.process(message, null,
                                                        new ReplyCode(), new CommandsController()));
    }
}