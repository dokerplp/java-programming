package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataChecker;
import com.company.utility.forData.DataOperator;
import com.company.exceptions.IncorrectDataException;

import java.util.Iterator;
import java.util.List;

/**
 * Command deletes element by id, or types that there are no element with such id
 */
public class RemoveByIdCommand implements Command {

    private int id; //Argument
    private final List<Product> products;
    private final DataOperator data;

    /**
     * RemoveByIdCommand Constructor
     * @param base - collection and data operator
     */
    public RemoveByIdCommand(DataBase base) {
        this.products = base.getBase();
        this.data = base.getOperator();
    }

    /**
     * Realization of this command
     */
    private void RemoveByIdRealization(){
        boolean trigger = false; //If trigger is true object will be removed
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()){
            Product NextProduct = iterator.next();
            if (NextProduct.getId() == id) {
                iterator.remove();
                trigger = true;
            }
        }
        if(!trigger)System.out.println("Такого элемента не существует");

    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument == null) System.err.println("Не хватает аргумета (Дробное число)");
        else {
            try {
                id = data.StringToInt(Argument);
                if (DataChecker.AboveZeroCheck(id)) RemoveByIdRealization();
                else System.err.println("Некорректный id");
            } catch (IncorrectDataException e){
                System.out.println("Введите целое число");
            }
        }
    }
}
