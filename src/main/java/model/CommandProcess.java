package model;

import controller.CommandsController;

import java.io.PrintWriter;

public interface CommandProcess {
   String process(String message, PrintWriter writer, ReplyCode code, CommandsController controller);
}
