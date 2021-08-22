package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;

/**
 * Command deletes all elements which price is more than some number
 */
public class RemoveGreaterCommand implements Command {

    private final Vault vault;

    public RemoveGreaterCommand(Vault vault) {
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
        vault.getCollection().removeIf(product -> product.getPrice() > command.getLongArgument());
        return new Answer("Элемент удален!");
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "remove_greater - удалит из коллекции все элементы, превышающие заданный\n";
    }

}
