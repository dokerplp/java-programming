package com.company.commandPattern;

import com.company.data.Product;
import com.company.commandPattern.commands.*;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataOperator;

import java.util.List;

/**
 * Receiver class
 * It knows list of commands, but dont knows how execute it
 */
public class CommandsOperator { //Receiver

    private final User user; //Pattern Invoker
    private final DataBase base; //Pattern Receiver

    /**
     * CommandOperator Constructor
     * @param user - invoker
     * @param base - base
     */
    public CommandsOperator(User user, DataBase base){
        this.user = user;
        this.base = base;
        SetCommands();
    }

    /**
     * Adding command in Invoker HashMap
     */
    private void SetCommands(){
        user.addCommand("help", new HelpCommand());
        user.addCommand("add", new AddCommand(base));
        user.addCommand("add_if_max", new AddIfMaxCommand(base));
        user.addCommand("average_of_price", new AverageOfPriceCommand(base));
        user.addCommand("clear", new ClearCommand(base));
        user.addCommand("count_by_manufacture_cost", new CountByManufactureCostCommand(base));
        user.addCommand("count_less_than_manufacture_cost", new CountLessThanManufactureCostCommand(base));
        user.addCommand("execute_script", new ExecuteScriptCommand(base));
        user.addCommand("exit", new ExitCommand());
        user.addCommand("info", new InfoCommand(base));
        user.addCommand("remove_by_id", new RemoveByIdCommand(base));
        user.addCommand("remove_greater", new RemoveGreaterCommand(base));
        user.addCommand("save", new SaveCommand(base));
        user.addCommand("show", new ShowCommand(base));
        user.addCommand("shuffle", new ShuffleCommand(base));
        user.addCommand("update", new UpdateCommand(base));
    }

    /**
     * Running execution in Invoker class
     * @param command - what command
     * @param argument - what argument
     */
    public void execute(String command, String argument){
        user.execute(command, argument);
    }




}
