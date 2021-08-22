package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;


/**
 * Command types average price of all products
 */
public class AverageOfPriceCommand implements Command {

    private final Vault vault;

    public AverageOfPriceCommand(Vault vault) {
        this.vault = vault;
    }


    @Override
    public Answer execute(Mail command, int id) {
        if (vault.isEmpty())
            return new Answer("Еще не существет ни одного продукта!", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
        else {
            double price = vault.average();
            return new Answer("Средняя цена: " + price, price < 0 ? RequestResult.REQUEST_FAILED : RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
        }
    }

    @Override
    public String help() {
        return "average_of_price - выведет среднее значение поля price для всех элементов коллекции\n";
    }
}
