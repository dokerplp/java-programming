package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;
import com.company.utility.database.SQL;

/**
 * Command deletes all elements which price is more than some number
 */
public class RemoveGreaterCommand implements Command {

    private final Vault vault;
    private final SQL sql;

    public RemoveGreaterCommand(Vault vault, SQL sql) {
        this.vault = vault;
        this.sql = sql;
    }

    @Override
    public Answer execute(Mail command, int id) {
        if (vault.isEmpty()) return new Answer("Такого элемента не существует", RequestResult.REQUEST_FAILED);
        if (sql.deleteHigher(command.getLongArgument(), id)) {
            vault.removeGreater(command.getLongArgument(), id);
            return new Answer("Элемент удален!", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
        } else return null;
    }

    @Override
    public String help() {
        return "remove_greater - удалит из коллекции все элементы, превышающие заданный\n";
    }

}
