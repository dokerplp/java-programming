package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forCommands.CreateNewProduct;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataOperator;
import com.company.exceptions.IncorrectDataException;

import java.util.List;

/**
 * Collection updates fields of element by its id
 */
public class UpdateCommand implements Command { //done //maybe //TESTED!

    private final DataOperator data;
    private Product update = null;
    private final DataBase base;
    private final List<Product> products;

    private final boolean trigger; //true - should write invitation; false - not

    /**
     * UpdateCommand Constructor
     * @param base - collection and data operator
     */
    public UpdateCommand(DataBase base) {
        this.base = base;
        this.data = base.getOperator();
        products = base.getBase();

        trigger = base.getQueue().isStatus();
    }

    /**
     * Realization of this command
     */
    private void UpdateRealization(){
        CreateNewProduct createNewProduct = new CreateNewProduct(update, base);
        System.out.println("Updating...");
        String now = "Текущее значение поля ";
        System.out.println(now + "\"Название продукта\": " + update.getName());
        if (Up()) createNewProduct.setName();
        if (trigger) System.out.println(now + "\"Координата X\": " + update.getCoordinates().getX());
        if (Up()) createNewProduct.setX();
        if (trigger) System.out.println(now + "\"Координата Y\": " + update.getCoordinates().getY());
        if (Up()) createNewProduct.setY();
        if (trigger) System.out.println(now + "\"Цена\": " + update.getPrice());
        if (Up()) createNewProduct.setPrice();
        if (trigger) System.out.println(now + "\"Артикул\": " + update.getPartNumber());
        if (Up()) createNewProduct.setPartNumber();
        if (trigger) System.out.println(now + "\"Цена производства\": " + update.getManufactureCost());
        if (Up()) createNewProduct.setMCost();
        if (trigger) System.out.println(now + "\"Единица измерения\": " + update.getUnitOfMeasure().getType());
        if (Up()) createNewProduct.setMeasure();
        if (trigger) System.out.println(now + "\"Название организации\": " + update.getManufacturer().getName());
        if (Up()) createNewProduct.setOrgName();
        if (trigger) System.out.println(now + "\"Количество работников\": " + update.getManufacturer().getEmployeesCount());
        if (Up()) createNewProduct.setEcount();
        if (trigger) System.out.println(now + "\"Тип компании\": " + update.getManufacturer().getType());
        if (Up()) createNewProduct.setType();
        if (trigger) System.out.println("\"Адрес\"");
        if (Up()) {
            if (createNewProduct.Null()) update.getManufacturer().setOfficialAddress(null);
            else {
                if (trigger) System.out.println(now + "\"Улица\": " + update.getManufacturer().getOfficialAddress().getStreet());
                if (Up()) createNewProduct.setName();
                if (trigger) System.out.println(now + "\"Почтовый код\": " + update.getManufacturer().getOfficialAddress().getZipCode());
                if (Up()) createNewProduct.setZip();
            }
        }

        System.out.println("\nИзменения сохранены!");
    }

    /**
     * This method checks, does the person want to update field
     * @return the person decision
     */
    private boolean Up(){
        System.out.println("Желаете обновить поле?\nПустое поле - нет, любой символ - да");
        String ans = data.getSTRING();
        return !(ans == null);
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument == null) System.err.println("Не хватает аргумета (Целое число)");
        else {
            try {
                int id = data.StringToInt(Argument);
                for (Product product: products){
                    if(product.getId() == id){
                        update = product;
                    }
                }
                if (update != null) UpdateRealization();
                else System.err.println("Такого id не существует!");
            }catch (IncorrectDataException e){
                System.out.println("Введите целое число");
            }
        }
    }
}
