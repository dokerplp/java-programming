package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;

import java.util.List;

/**
 * Command deletes all elements in collection
 */
public class ClearCommand implements Command {

    private final List<Product> products;

    /**
     * AddIfMaxCommand Constructor
     * @param base - collection
     */
    public ClearCommand(DataBase base) {
        this.products = base.getBase();
    }

    /**
     * Realization of this command
     */
    private void ClearRealization(){
        products.clear();
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.err.println("Эта комманда не требует никаих аргуметов так-то :)");
        ClearRealization();
    }
}
