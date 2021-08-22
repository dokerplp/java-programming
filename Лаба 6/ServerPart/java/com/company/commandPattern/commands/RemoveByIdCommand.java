package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;


/**
 * Command deletes element by id, or types that there are no element with such id
 */
public class RemoveByIdCommand implements Command {

    private final Vault vault;

    public RemoveByIdCommand(Vault vault) {
        this.vault = vault;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {
        if (vault.getCollection().isEmpty()) return new Answer("Такого элемента не существует");
        vault.getCollection().removeIf(product -> product.getId() == command.getIntArgument());
        return new Answer("Элемент удален!");
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "remove_by_id - удалит элемент из коллекции по его id\n";
    }
}
