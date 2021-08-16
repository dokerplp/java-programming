package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.utility.forData.DataBase;

import java.util.List;

/**
 * Collection types all fields of each element
 */
public class ShowCommand implements Command {

    private final List<Product> products;

    /**
     * AddCommand Constructor
     * @param base - collection
     */
    public ShowCommand(DataBase base) {
        this.products = base.getBase();
    }

    /**
     * Realization of this command
     */
    private void ShowRealization(){
        int i = 0; //Product number
        for (Product product : products){
            i++;
            System.out.println(
                            "Продукт номер " + i + "\n" +
                            "ID продукта: " + product.getId() + "\n" +
                            "Название продукта: " + product.getName() + "\n" +
                            "Координаты: " + "\n" +
                            "     X: " + product.getCoordinates().getX() + "\n" +
                            "     Y: " + product.getCoordinates().getY() + "\n" +
                            "Дата создания: " + product.getCreationDate() + "\n" +
                            "Цена: " + product.getPrice() + "\n" +
                            "Артикул : " + product.getPartNumber() + "\n" +
                            "Стоимость изготовления: " + product.getManufactureCost() + "\n" +
                            "Единица измерения: " + product.getUnitOfMeasure().getType() + "\n" +
                            "Изготовитель: " + "\n" +
                            "     ID компании: " + product.getManufacturer().getId() + "\n" +
                            "     Название компании: " + product.getManufacturer().getName() + "\n" +
                            "     Количество работников: " + product.getManufacturer().getEmployeesCount()+ "\n" +
                            "     Тип компании: " + product.getManufacturer().getType().getType()
            );
            if (product.getManufacturer().getOfficialAddress() == null) System.out.println("     Адрес: null \n");
            else System.out.println(
                            "     Адрес: \n" +
                            "          Улица: " + product.getManufacturer().getOfficialAddress().getStreet() + "\n" +
                            "          Почтовый индекс: " + product.getManufacturer().getOfficialAddress().getZipCode() + "\n");
        }
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.err.println("Эта комманда не требует никаих аргуметов так-то :)");
        ShowRealization();
    }

}
