package model;

import controller.*;

import java.util.Map;
import java.util.TreeMap;


public class MapOfCommand {
    public Map commands;

   public  MapOfCommand(){
        commands = new TreeMap<String, CommandProcess>();

        commands.put("CWD", new CommandCWD());
        commands.put("USER", new CommandUSER());
        commands.put("PASS", new CommandPASS());
        commands.put("DELE", new CommandDELE());
        commands.put("RMD", new  CommandRMD());
        commands.put("MKD", new  CommandMKD());
        commands.put("STOR", new CommandSTOR());
        commands.put("PORT", new CommandPORT());
        commands.put("RETR", new CommandRETR());
        commands.put("LIST",new  CommandLIST());
        commands.put("PWD", new CommandPWD());
        commands.put("SYST", new CommandSYST());
        commands.put("TYPE", new CommandTYPE());
        commands.put("PASV", new CommandPASV());
        commands.put("CDUP", new CommandCDUP());
    }

}
