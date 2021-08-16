package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;
import com.company.utility.database.SQL;


/**
 * Command create and add new element to collection, if its price is more then u̶n̶i̶v̶e̶r̶s̶i̶t̶y̶ max price
 */
public class AddIfMaxCommand implements Command {

    private final SQL sql;
    private final Vault vault;

    public AddIfMaxCommand(SQL sql, Vault vault) {
        this.sql = sql;
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        Product p = command.getProductArgument();
        if (p == null) return null;
        if (vault.higherPrice(p.getPrice()))
            return new Answer("\nНовый продукт не превышает максимального, создание отменено!", RequestResult.REQUEST_FAILED);
        else {
            Product product = sql.insertProduct(p, id);
            if (product != null) {
                vault.addOne(product);
                return new Answer("\nПродукт создан!", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
            }
            return null;
        }
    }

    @Override
    public String help() {
        return "add_if_max - добавит новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n";
    }
}
