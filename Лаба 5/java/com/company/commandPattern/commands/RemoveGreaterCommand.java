package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataOperator;
import com.company.exceptions.IncorrectDataException;

import java.util.Iterator;
import java.util.List;

/**
 * Command deletes all elements which price is more than some number
 */
public class RemoveGreaterCommand implements Command {

    private final List<Product> products;
    private final DataOperator data;
    private long price; //Argument

    /**
     * RemoveGreaterCommand Constructor
     * @param base - collection and data operator
     */
    public RemoveGreaterCommand(DataBase base) {
        this.products = base.getBase();
        this.data = base.getOperator();
    }

    /**
     * Realization of this command
     */
    private void RemoveGreaterRealization(){
        boolean trigger = false; //If trigger is true object will be removed
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()){
            Product NextProduct = iterator.next();
            if (NextProduct.getPrice() > price) {
                iterator.remove();
                trigger = true;
            }
        }
        if(!trigger)System.err.println("Такого элемента не существует");
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument == null) System.err.println("Не хватает аргумета (Целое число)");
        else {
            try {
                price = data.StringToInt(Argument);
                RemoveGreaterRealization();
            } catch (IncorrectDataException e){
                System.out.println("Введите целое число");
            }
        }
    }
}
