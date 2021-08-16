package com.company.commandPattern;

import com.company.commandPattern.commands.*;
import com.company.memory.Vault;
import com.company.utility.database.SQL;


/**
 * Receiver class
 * It knows list of commands, but dont knows how execute it
 */
public class CommandsOperator {

    private final SQL sql;
    private final Vault vault;

    public CommandsOperator(SQL sql, Vault vault) {
        this.sql = sql;
        this.vault = vault;
    }

    /**
     * Adding command in Invoker HashMap
     */
    public void setCommands(User user) {
        user.addCommand("help", new HelpCommand());
        user.addCommand("add", new AddCommand(sql, vault));
        user.addCommand("add_if_max", new AddIfMaxCommand(sql, vault));
        user.addCommand("average_of_price", new AverageOfPriceCommand(vault));
        user.addCommand("clear", new ClearCommand(sql, vault));
        user.addCommand("count_by_manufacture_cost", new CountByManufactureCostCommand(vault));
        user.addCommand("count_less_than_manufacture_cost", new CountLessThanManufactureCostCommand(vault));
        user.addCommand("info", new InfoCommand(vault));
        user.addCommand("remove_by_id", new RemoveByIdCommand(sql, vault));
        user.addCommand("remove_greater", new RemoveGreaterCommand(vault, sql));
        user.addCommand("show", new ShowCommand(vault));
        user.addCommand("shuffle", new ShuffleCommand(vault));
        user.addCommand("update_one", new UpdateCommand(sql, vault));
        user.addCommand("update_two", new UpdateCommand(sql, vault));
    }
}
