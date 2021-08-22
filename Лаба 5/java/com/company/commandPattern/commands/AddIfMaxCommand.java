package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forCommands.CreateNewProduct;
import com.company.utility.forData.DataBase;

import java.util.List;

/**
 * Command create and add new element to collection, if its price is more then u̶n̶i̶v̶e̶r̶s̶i̶t̶y̶ max price
 */
public class AddIfMaxCommand implements Command {

    private final DataBase base;
    private final List<Product> products;

    /**
     * AddIfMaxCommand Constructor
     * @param base - collection
     */
    public AddIfMaxCommand(DataBase base) {
        this.base = base;
        products = base.getBase();
    }

    /**
     * Realization of this command
     */
    private void AddIfMaxRealization(){
        CreateNewProduct createNewProduct = new CreateNewProduct(base);
        Product product = createNewProduct.HumanMode();
        boolean trigger = true; //if trigger is false, then creation will be canceled
        long price = product.getPrice(); //price is a comparison criterion
        for (Product product2 : products){ //Check all prices
            if (product2.getPrice() >= price) { //If new price isn't the highest
                trigger = false;
                break;
            }
        }
        if (trigger){
            products.add(product);
            base.getFields().setLastInit(); //Set new Initialization time
            System.out.println("\nПродукт создан!");
        }
        else {
            System.err.println("\nНовый продукт не превышает максимального, создание отменено!");
        }

    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.err.println("Эта комманда не требует никаих аргуметов так-то :)");
        AddIfMaxRealization();
    }
}
