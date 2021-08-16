package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;

import java.util.Collections;
import java.util.List;

/**
 * Collection types all fields of each element
 */
public class ShowCommand implements Command {

    private final Vault vault;

    public ShowCommand(Vault vault) {
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        List<Product> dup = vault.getClone();
        Collections.sort(dup);
        return new Answer("show command", dup, RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    @Override
    public String help() {
        return "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении\n";
    }

}
