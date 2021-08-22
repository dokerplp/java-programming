package com.company.commands;

import com.company.data.Product;
import com.company.data.io.Answer;

import java.util.List;

public class ShowCommand {

    public static String execute(Answer answer) {

        List<Product> list = answer.getProducts();

        StringBuilder res = new StringBuilder();

        list.stream().map(product -> {
                    String one;
                    String two;
                    one = "ID продукта: " + product.getId() + "\n" +
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
                            "     Количество работников: " + product.getManufacturer().getEmployeesCount() + "\n" +
                            "     Тип компании: " + product.getManufacturer().getType().getType() + "\n";
                    if (product.getManufacturer().getOfficialAddress() == null) two = "     Адрес: null \n";
                    else
                        two = "     Адрес: \n" +
                                "          Улица: " + product.getManufacturer().getOfficialAddress().getStreet() + "\n" +
                                "          Почтовый индекс: " + product.getManufacturer().getOfficialAddress().getZipCode() + "\n";
                    return one + two;
                }
        ).forEach(res::append);
        if (res.toString().equals("")) res.append("Пусто");

        return res.toString();
    }
}
