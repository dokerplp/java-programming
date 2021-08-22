package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;
import com.company.utility.database.SQL;


/**
 * Command create and add new element to collection
 */
public class AddCommand implements Command {

    private final SQL sql;
    private final Vault vault;

    public AddCommand(SQL sql, Vault vault) {
        this.sql = sql;
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {

        Product product = command.getProductArgument();
        if (product == null) return null;
        Product p = sql.insertProduct(product, id);
        if (p != null) {
            vault.addOne(p);
            return new Answer("\nПродукт создан!", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
        }
        return null;
    }

    @Override
    public String help() {
        return "add - добавит новый элемент в коллекцию\n";
    }
}