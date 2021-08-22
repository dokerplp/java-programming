package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.memory.Vault;


/**
 * Command counts amount of elements which manufacture cost is less than some number
 */
public class CountLessThanManufactureCostCommand implements Command {

    private final Vault vault;

    public CountLessThanManufactureCostCommand(Vault vault) {
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command, int id) {
        return new Answer("" + vault.countLessThanManufactureCost(command.getFloatArgument()), RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    @Override
    public String help() {
        return "count_less_than_manufacture_cost - выведет количество элементов, значение поля manufactureCost которых меньше заданного\n";
    }
}
