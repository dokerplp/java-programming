package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.commandPattern.Remove;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;
import com.company.utility.database.SQL;


/**
 * Command deletes element by id, or types that there are no element with such id
 */
public class RemoveByIdCommand implements Command {

    private final SQL sql;
    private final Vault vault;

    public RemoveByIdCommand(SQL sql, Vault vault) {
        this.sql = sql;
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        if (vault.isEmpty()) return new Answer("Такого элемента не существует", RequestResult.REQUEST_FAILED);
        if (sql.deleteByID(command.getIntArgument(), id)) {
            Remove remove = vault.removeById(command.getIntArgument(), id);
            if (remove == Remove.WRONG_CLIENT_ID)
                return new Answer("Этот обьект не принадлежит вам", RequestResult.REQUEST_FAILED);
            else if (remove == Remove.WRONG_PRODUCT_ID)
                return new Answer("Такого элемента не существует", RequestResult.REQUEST_FAILED);
        }
        return new Answer("Элемент удален!", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    @Override
    public String help() {
        return "remove_by_id - удалит элемент из коллекции по его id\n";
    }
}
