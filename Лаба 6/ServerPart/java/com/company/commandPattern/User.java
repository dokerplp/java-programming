package com.company.commandPattern;

import com.company.data.io.Answer;
import com.company.data.io.Mail;

import java.util.HashMap;

/**
 * Invoker class
 * It knows how how add and execute commands, but dont know list of commands
 */
public class User {

    /**
     * Map of commands
     */
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public HashMap<String, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * This method adds command to map
     *
     * @param Name    - command's name
     * @param command - link to the one of the commands
     */
    public void addCommand(String Name, Command command) {
        commandMap.put(Name, command);
    }

    /**
     * Executing of command
     *
     * @param Name - command's name
     */
    public Answer execute(String Name, Mail c) {
        Command command = commandMap.get(Name);
        if (command == null) {
            System.err.println("Такой комманды не существует");
        } else {
            return command.execute(c);
        }
        return null;
    }


}