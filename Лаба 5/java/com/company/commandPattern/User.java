package com.company.commandPattern;

import java.util.HashMap;

/**
 * Invoker class
 * It knows how how add and execute commands, but dont know list of commands
 */
public class User { //Invoker

    /**
     * Map of commands
     */
    private final HashMap<String, Command> commandMap = new HashMap<>();

    /**
     * This method adds command to map
     * @param Name - command's name
     * @param command - link to the one of the commands
     */
    public void addCommand(String Name, Command command){
        commandMap.put(Name, command);
    }

    /**
     * Executing of command
     * @param Name - command's name
     * @param Argument - command's argument
     */
    public void execute(String Name, String Argument){
        Command command = commandMap.get(Name);
        if (command == null){
            System.err.println("Такой комманды не существует");
        }
        else {
            command.execute(Argument);
        }
    }


}