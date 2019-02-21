package model;

import controller.CommandsController;

public interface CommandProccess {
   String process(String message, CommandsController controller);
}
