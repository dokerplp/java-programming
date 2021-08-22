package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;

import java.util.List;

/**
 * Command types average price of all products
 */
public class AverageOfPriceCommand implements Command {

    private final List<Product> products; //Collection

    /**
     * AddIfMaxCommand Constructor
     * @param base - collection
     */
    public AverageOfPriceCommand(DataBase base) {
        products = base.getBase();
    }

    /**
     * Realization of this command
     */
    private void AverageOfPriceRealization(){
        long Average = 0; //Sum of all prices
        int Counter = 0; //Amount of products
        for(Product product : products){
            Average += product.getPrice();
            Counter++;
        }
        if (Counter != 0) {
            System.out.println("Средняя цена: " + Average/Counter);
        }
        else System.err.println("Еще не существет ни одного продукта!");
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.err.println("Эта комманда не требует никаих аргуметов так-то :)");
        AverageOfPriceRealization();
    }
}
