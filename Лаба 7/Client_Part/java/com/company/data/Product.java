package com.company.data;

import com.company.memory.Registers;
import com.company.utility.Input;
import com.company.utility.staticUtil.DataOperator;
import com.company.utility.staticUtil.ProductOperator;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * This class has information about product
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int userId;
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    ////////////////////////////////////////////////////////////////////////
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final long price; //Значение поля должно быть больше 0
    private final String partNumber; //Поле может быть null
    private final UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private float manufactureCost;
    private Organization manufacturer; //Поле не может быть null

    public Product(Registers registers, ProductOperator operator, DataOperator data) {
        this.userId = -1;
        this.id = -1;
        this.name = operator.NAME();
        this.coordinates = new Coordinates(registers, operator);
        this.creationDate = null;
        this.price = operator.PRICE();
        this.partNumber = operator.PART_NUMBER();
        this.manufactureCost = operator.COST();
        this.unitOfMeasure = operator.MEASURE();
        this.manufacturer = new Organization(registers, operator, data);
    }

    ////////////////////////////////////////////////////////////////////////

    public Product(Product product, Registers registers, ProductOperator operator) {

        this.userId = product.getUserId();

        boolean trigger = registers.isMode();

        String now = "Текущее значение поля ";

        this.id = product.getId();

        if (trigger) System.out.println(now + "\"Название продукта\": " + product.getName());
        if (ask()) this.name = operator.NAME();
        else this.name = product.getName();

        if (trigger) System.out.println("Координаты");
        if (ask()) this.coordinates = new Coordinates(product.getCoordinates(), registers, operator);
        else this.coordinates = product.getCoordinates();

        this.creationDate = product.getCreationDate();

        if (trigger) System.out.println(now + "\"Цена\": " + product.getPrice());
        if (ask()) this.price = operator.PRICE();
        else this.price = product.getPrice();

        if (trigger) System.out.println(now + "\"Артикул\": " + product.getPartNumber());
        if (ask()) this.partNumber = operator.PART_NUMBER();
        else this.partNumber = product.getPartNumber();

        if (trigger) System.out.println(now + "\"Цена производства\": " + product.getManufactureCost());
        if (ask()) this.manufactureCost = operator.COST();
        else this.manufacturer = product.getManufacturer();

        if (trigger) System.out.println(now + "\"Единица измерения\": " + product.getUnitOfMeasure().toString());
        if (ask()) this.unitOfMeasure = operator.MEASURE();
        else this.unitOfMeasure = product.getUnitOfMeasure();

        if (trigger) System.out.println("Организация");
        if (ask()) this.manufacturer = new Organization(product.getManufacturer(), registers, operator);
        else this.manufacturer = product.getManufacturer();
    }

    public static boolean ask() {
        System.out.println("Желаете обновить поле?\nПустое поле - нет, любой символ - да");
        String ans = Input.SCANNER.nextLine();
        return !(ans == null || ans.equals("") || ans.equals("null"));
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getPrice() {
        return price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public float getManufactureCost() {
        return manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }
}
