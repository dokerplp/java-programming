package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;


/**
 * Command counts amount of elements which manufacture cost is equals to some number
 */
public class CountByManufactureCostCommand implements Command {

    private final Vault vault;

    public CountByManufactureCostCommand(Vault vault) {
        this.vault = vault;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {
        return new Answer("" + vault.getCollection().stream().filter(product -> product.getManufactureCost() == command.getFloatArgument()).count());
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "count_by_manufacture_cost - выведет количество элементов, значение поля manufactureCost которых равно заданному\n";
    }
}
