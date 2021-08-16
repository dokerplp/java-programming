package com.company.utility;

import com.company.data.Product;
import com.company.data.io.Mail;
import com.company.exceptions.Exit;
import com.company.exceptions.InterruptionFetch;
import com.company.exceptions.NeedArgumentException;
import com.company.exceptions.NotExistingCommandException;
import com.company.memory.Registers;
import com.company.memory.Vault;
import com.company.utility.staticUtil.DataConverter;
import com.company.utility.staticUtil.DataOperator;
import com.company.utility.staticUtil.ProductOperator;
import com.company.utility.staticUtil.commands.ExecuteScript;


public class CommandCreator {

    private final Authentication authentication;

    private final Vault vault;
    private final Registers registers;
    private final ProductOperator operator;
    private final DataOperator data;

    public CommandCreator(Authentication authentication, Vault vault, Registers registers, ProductOperator operator, DataOperator data) {
        this.authentication = authentication;
        this.vault = vault;
        this.registers = registers;
        this.operator = operator;
        this.data = data;
    }

    /**
     * Mail creator
     *
     * @param command  - command name
     * @param argument - command arg
     * @return Mail
     */
    public Mail command(String command, String argument) {
        String LOGIN = authentication.getLOGIN();
        String PASS = authentication.getPASS();
        switch (command) {
            case "add":
            case "add_if_max":
                return new Mail(command, new Product(registers, operator, data), LOGIN, PASS);
            case "average_of_price":
            case "clear":
            case "help":
            case "info":
            case "show":
            case "shuffle":
                return new Mail(command, LOGIN, PASS);
            case "count_by_manufacture_cost":
            case "count_less_than_manufacture_cost":
                if (argument == null) throw new NeedArgumentException("Дробное число");
                return new Mail(command, DataConverter.StringToFloat(argument), LOGIN, PASS);
            case "exit":
                throw new Exit();
            case "remove_by_id":
                if (argument == null) throw new NeedArgumentException("Целое число");
                return new Mail(command, DataConverter.StringToInt(argument), LOGIN, PASS);
            case "remove_greater":
                if (argument == null) throw new NeedArgumentException("Целое число");
                return new Mail(command, DataConverter.StringToLong(argument), LOGIN, PASS);
            case "execute_script":
                if (argument == null) throw new NeedArgumentException("Адрес скрипта");
                new ExecuteScript(vault, registers).execute(argument);
                throw new InterruptionFetch();
            case "update":
                if (argument == null) {
                    throw new NeedArgumentException("Не хватает аргумента (Целое число)");
                } else {
                    return new Mail("update_one", DataConverter.StringToInt(argument), LOGIN, PASS);
                }
            default:
                throw new NotExistingCommandException();
        }
    }

    public Mail command(Product product) {
        return new Mail("update_two", new Product(product, registers, operator), authentication.getLOGIN(), authentication.getPASS());
    }
}
