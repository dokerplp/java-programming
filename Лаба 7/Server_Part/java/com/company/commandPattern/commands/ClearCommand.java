package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;
import com.company.utility.database.SQL;

/**
 * Command deletes all elements in collection
 */
public class ClearCommand implements Command {

    private final SQL sql;
    private final Vault vault;

    public ClearCommand(SQL sql, Vault vault) {
        this.sql = sql;
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        if (sql.deleteAll(id)) vault.removeAll(id);
        return new Answer("Коллекция очищена", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    @Override
    public String help() {
        return "clear - очистит коллекцию\n";
    }
}
