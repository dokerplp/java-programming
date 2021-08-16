package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.utility.forCommands.CreateNewProduct;
import com.company.data.Product;
import com.company.utility.forData.DataBase;

import java.util.List;

/**
 * Command create and add new element to collection
 */
public class AddCommand implements Command {

    private final DataBase base;
    private final List<Product> products;

    /**
     * AddCommand Constructor
     * @param base - collection
     */
    public AddCommand(DataBase base) {
        this.base = base;
        products = base.getBase();
    }

    /**
     * Realization of this command
     */
    private void AddRealization(){
        CreateNewProduct createNewProduct = new CreateNewProduct(base); //Class for creation class
        Product product = createNewProduct.HumanMode();
        products.add(product);
    }


    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.out.println("Эта комманда не требует никаих аргуметов так-то :)");
        AddRealization();
        base.getFields().setLastInit(); //New Initialization Time
        System.out.println("\nПродукт создан!");
    }
}