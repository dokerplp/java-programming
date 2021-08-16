package com.company.commands;

import com.company.data.Product;
import com.company.data.io.Mail;
import com.company.exceptions.InterruptionFetch;
import com.company.exceptions.NeedArgumentException;
import com.company.exceptions.NotExistingCommandException;
import com.company.memory.Registers;
import com.company.memory.Vault;
import com.company.utility.nonstaticUtil.DataOperator;
import com.company.utility.nonstaticUtil.ProductOperator;
import com.company.utility.staticUtil.DataConverter;


public class CommandCreator {

    //Delegates
    private final ProductOperator operator;
    private final Registers registers;
    private final DataOperator data;
    private final Vault buffer;

    /**
     * Constructor
     *
     * @param operator  - utility for creating new product
     * @param registers - trigger for human mode or auto mode
     * @param data      - utility for taking client request
     * @param vault     - vault of client requests
     */
    public CommandCreator(ProductOperator operator, Registers registers, DataOperator data, Vault vault) {
        this.operator = operator;
        this.registers = registers;
        this.data = data;
        this.buffer = vault;
    }

    /**
     * Mail creator
     *
     * @param command  - command name
     * @param argument - command arg
     * @return Mail
     */
    public Mail command(String command, String argument) {
        switch (command) {
            case "add":
            case "add_if_max":
                return new Mail(command, new Product(operator, registers, data));
            case "average_of_price":
            case "clear":
            case "help":
            case "info":
            case "show":
            case "shuffle":
                return new Mail(command);
            case "count_by_manufacture_cost":
            case "count_less_then_manufacture_cost":
                if (argument == null) throw new NeedArgumentException("Дробное число");
                return new Mail(command, DataConverter.StringToFloat(argument));
            case "exit":
                System.exit(0);
            case "remove_by_id":
                if (argument == null) throw new NeedArgumentException("Целое число");
                return new Mail(command, DataConverter.StringToInt(argument));
            case "remove_greater":
                if (argument == null) throw new NeedArgumentException("Целое число");
                return new Mail(command, DataConverter.StringToLong(argument));
            case "execute_script":
                if (argument == null) throw new NeedArgumentException("Адрес скрипта");
                new ExecuteScript(buffer, registers, argument);
                throw new InterruptionFetch();
            case "update":
                if (argument == null) {
                    throw new NeedArgumentException("Не хватает аргумента (Целое число)");
                } else {
                    return new Mail("update_one", DataConverter.StringToInt(argument));
                }
            default:
                throw new NotExistingCommandException();
        }
    }

    public Mail command(Product product) {
        return new Mail("update_two", new Product(operator, registers, data, product));
    }
}
