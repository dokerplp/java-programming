package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataOperator;
import com.company.exceptions.IncorrectDataException;

import java.util.List;

/**
 * Command counts amount of elements which manufacture cost is less than some number
 */
public class CountLessThanManufactureCostCommand implements Command {

    private final List<Product> products;
    private final DataOperator data;
    private float manufactureCost; //Argument


    /**
     * CountLessThanManufactureCostCommand Constructor
     * @param base - collection and data operator
     */
    public CountLessThanManufactureCostCommand(DataBase base) {
        this.products = base.getBase();
        this.data = base.getOperator();
    }

    /**
     * Realization of this command
     */
    private void CountLessThanManufactureCostRealization(){
        int counter = 0;
        for (Product product : products){
            if (product.getManufactureCost() < manufactureCost) counter++;
        }
        System.out.println(counter);
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument == null) System.err.println("Не хватает аргумета (Дробное число)");
        else {
            try {
                manufactureCost = data.StringToFloat(Argument);
                CountLessThanManufactureCostRealization();
            } catch (IncorrectDataException e){
                System.out.println("Введите дробное число");
            }
        }
    }
}
