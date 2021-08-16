package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataOperator;
import com.company.exceptions.IncorrectDataException;

import java.util.List;

/**
 * Command counts amount of elements which manufacture cost is equals to some number
 */
public class CountByManufactureCostCommand implements Command {

    private final List<Product> products;
    private final DataOperator data;
    private float manufactureCost; //Argument


    /**
     * CountByManufactureCostCommand Constructor
     * @param base - collection and data operator
     */
    public CountByManufactureCostCommand(DataBase base) {
        this.products = base.getBase();
        this.data = base.getOperator();
    }

    /**
     * Realization of this command
     */
    private void CountByManufactureCostRealization(){
        int counter = 0; //Answer
        for (Product product : products){
            if (product.getManufactureCost() == manufactureCost) counter++;
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
                CountByManufactureCostRealization();
            } catch (IncorrectDataException e){
                System.out.println("Введите дробное число");
            }
        }
    }
}
