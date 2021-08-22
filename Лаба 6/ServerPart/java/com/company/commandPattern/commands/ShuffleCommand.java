package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;

import java.util.Collections;


/**
 * Command shuffles collection
 */
public class ShuffleCommand implements Command {

    private final Vault vault;

    public ShuffleCommand(Vault vault) {
        this.vault = vault;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {
        Collections.shuffle(vault.getCollection());
        return new Answer("Коллекция перемешана!");
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "shuffle - перемешает элементы коллекции в случайном порядке\n";
    }
}
