package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;


/**
 * Command shuffles collection
 */
public class ShuffleCommand implements Command {

    private final Vault vault;

    public ShuffleCommand(Vault vault) {
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        vault.shuffle();
        return new Answer("Коллекция перемешана!", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    @Override
    public String help() {
        return "shuffle - перемешает элементы коллекции в случайном порядке\n";
    }
}
