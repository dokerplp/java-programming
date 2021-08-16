package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;

/**
 * Command deletes all elements in collection
 */
public class ClearCommand implements Command {

    private final Vault vault;

    public ClearCommand(Vault vault) {
        this.vault = vault;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {
        vault.getCollection().clear();
        return new Answer("Коллекция очищена");
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "clear - очистит коллекцию\n";
    }
}
