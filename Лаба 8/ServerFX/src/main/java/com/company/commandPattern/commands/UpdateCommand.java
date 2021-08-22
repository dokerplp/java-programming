package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;
import com.company.utility.Log;
import com.company.utility.database.SQL;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;


/**
 * Collection updates fields of element by its id
 */
public class UpdateCommand implements Command {

    private final SQL sql;
    private final Log log = new Log();
    private final Vault vault;

    public UpdateCommand(SQL sql, Vault vault) {
        this.sql = sql;
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        if (command.getCommandName().equals("update_one")) return executeOne(command, id);
        else if (command.getCommandName().equals("update_two")) return executeTwo(command, id);
        return null;
    }

    private Answer executeOne(Mail mail, int user) {
        try {
            int id = mail.getIntArgument();
            try (
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos)
            ) {
                List<Product> list = vault.prodById(id);
                if (list.isEmpty()) {
                    oos.writeObject(null);
                    return new Answer("Такого элемента не найдено", RequestResult.REQUEST_FAILED);
                } else if (list.get(0).getUserId() != user)
                    return new Answer("У вас нет прав на редактирование данного обьекта", RequestResult.REQUEST_FAILED);
                else return new Answer("update", list, RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
            }
        } catch (IOException e) {
            log.exception(e);
            return null;
        }
    }

    private Answer executeTwo(Mail mail, int user) {
        Product product = mail.getProductArgument();
        if (product == null) return null;
        if (sql.updateProduct(product)) {
            vault.removeById(product.getId(), user);
            vault.addOne(product);
        }
        return new Answer("\nПродукт обновлен!", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "update - обновит значение элемента коллекции, id которого равен заданному\n";
    }
}
