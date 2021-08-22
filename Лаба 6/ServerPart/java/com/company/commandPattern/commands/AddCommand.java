package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;


/**
 * Command create and add new element to collection
 */
public class AddCommand implements Command {

    private final Vault vault;

    /**
     * Constructor
     *
     * @param vault - collection and registers
     */
    public AddCommand(Vault vault) {
        this.vault = vault;
    }


    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {

        Product product = command.getProductArgument();
        if (product == null) return null;
        product.setId(vault.getRegisters().ProdNewID());
        product.getManufacturer().setId(vault.getRegisters().OrgNewID());
        product.newCreationDate();
        vault.getCollection().add(product);
        vault.getRegisters().setLastInit();
        return new Answer("\nПродукт создан!");
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "add - добавит новый элемент в коллекцию\n";
    }
}