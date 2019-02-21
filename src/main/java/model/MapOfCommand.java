package model;

import controller.*;

import java.util.Map;
import java.util.TreeMap;


public class MapOfCommand {
    public Map commands;

   public  MapOfCommand(){
        commands = new TreeMap<String, Command>();

        commands.put(Command.CWD, new CommandCWD());
        commands.put(Command.USER, new CommandUSER());
        commands.put(Command.PASS, new CommandPASS());
        commands.put(Command.DELE, new CommandDELE());
        commands.put(Command.RMD, new  CommandRMD());
        commands.put(Command.MKD, new  CommandMKD());
        commands.put(Command.STOR, new CommandSTOR());
        commands.put(Command.PORT, new CommandPORT());
        commands.put(Command.RETR, new CommandRETR());
        commands.put(Command.LIST,new  CommandLIST());
        commands.put(Command.PWD, new CommandPWD());
        commands.put(Command.SYST, new CommandSYST());
        commands.put(Command.TYPE, new CommandTYPE());
        commands.put(Command.PASV, new CommandPASV());
    }

}
