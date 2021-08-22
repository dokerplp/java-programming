package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;


/**
 * Command types average price of all products
 */
public class AverageOfPriceCommand implements Command {

    private final Vault vault;

    public AverageOfPriceCommand(Vault vault) {
        this.vault = vault;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {
        if (vault.getCollection().isEmpty())
            return new Answer("Еще не существет ни одного продукта!");
        else
            return new Answer("Средняя цена: " + vault.getCollection().stream().mapToLong(Product::getPrice).average().getAsDouble());
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "average_of_price - выведет среднее значение поля price для всех элементов коллекции\n";
    }
}
