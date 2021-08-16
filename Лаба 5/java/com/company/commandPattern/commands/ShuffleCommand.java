package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;

import java.util.Collections;
import java.util.List;

/**
 * Command shuffles collection
 */
public class ShuffleCommand implements Command {

    private final List<Product> products; //Collection

    /**
     * AddCommand Constructor
     * @param base - collection
     */
    public ShuffleCommand(DataBase base) {
        this.products = base.getBase();
    }

    /**
     * Realization of this command
     */
    private void ShuffleRealization(){
        Collections.shuffle(products);
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.err.println("Эта комманда не требует никаих аргуметов так-то :)");
        ShuffleRealization();
    }
}
