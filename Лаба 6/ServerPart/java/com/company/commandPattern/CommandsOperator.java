package com.company.commandPattern;

import com.company.commandPattern.commands.*;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;


/**
 * Receiver class
 * It knows list of commands, but dont knows how execute it
 */
public class CommandsOperator {

    private final User user;
    private final Vault vault;

    /**
     * CommandOperator Constructor
     *
     * @param user - invoker
     */
    public CommandsOperator(User user, Vault vault) {
        this.user = user;
        this.vault = vault;
        SetCommands();
    }

    /**
     * Adding command in Invoker HashMap
     */
    private void SetCommands() {
        user.addCommand("help", new HelpCommand(user.getCommandMap()));
        user.addCommand("add", new AddCommand(vault));
        user.addCommand("add_if_max", new AddIfMaxCommand(vault));
        user.addCommand("average_of_price", new AverageOfPriceCommand(vault));
        user.addCommand("clear", new ClearCommand(vault));
        user.addCommand("count_by_manufacture_cost", new CountByManufactureCostCommand(vault));
        user.addCommand("count_less_than_manufacture_cost", new CountLessThanManufactureCostCommand(vault));
        user.addCommand("info", new InfoCommand(vault));
        user.addCommand("remove_by_id", new RemoveByIdCommand(vault));
        user.addCommand("remove_greater", new RemoveGreaterCommand(vault));
        user.addCommand("show", new ShowCommand(vault));
        user.addCommand("shuffle", new ShuffleCommand(vault));
        user.addCommand("update_one", new UpdateCommand(vault));
        user.addCommand("update_two", new UpdateCommand(vault));
    }

    /**
     * Running execution in Invoker class
     *
     * @param command - what command
     */
    public Answer execute(Mail command) {
        //if (command == null) return null;
        return user.execute(command.getCommandName(), command);
    }


}
