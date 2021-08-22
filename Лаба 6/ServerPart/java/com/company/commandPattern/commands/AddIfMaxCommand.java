package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;


/**
 * Command create and add new element to collection, if its price is more then u̶n̶i̶v̶e̶r̶s̶i̶t̶y̶ max price
 */
public class AddIfMaxCommand implements Command {

    private final Vault vault;

    public AddIfMaxCommand(Vault vault) {
        this.vault = vault;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {
        Product p = command.getProductArgument();
        if (p == null) return null;
        if (vault.getCollection().stream().anyMatch(product -> product.getPrice() > p.getPrice()))
            return new Answer("\nНовый продукт не превышает максимального, создание отменено!");
        else {
            p.setId(vault.getRegisters().ProdNewID());
            p.getManufacturer().setId(vault.getRegisters().OrgNewID());
            p.newCreationDate();
            vault.getCollection().add(p);
            vault.getRegisters().setLastInit();
            return new Answer("\nПродукт создан!");
        }
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "add_if_max - добавит новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n";
    }
}
