package com.company.commandPattern.commands;

import com.company.commandPattern.Command;

/**
 * Command stops the program
 */
public class ExitCommand implements Command {

    /**
     * Realization of this command
     */
    private void ExitRealization(){
        System.exit(0);
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.err.println("Эта комманда не требует никаих аргуметов так-то :)");
        ExitRealization();
    }
}
