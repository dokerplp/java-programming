package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;

/**
 * Command types information about collection
 */
public class InfoCommand implements Command {

    private final Vault vault;

    public InfoCommand(Vault vault) {
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        return new Answer("Салам, сведения о коллекции:\n" +
                "Тип: простой список обьектов\n" +
                "Количество элементов: " + vault.getSize() + "\n" +
                "Последняя инициализация: " + vault.getLastInit(), RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    @Override
    public String help() {
        return "info - выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n";
    }
}
