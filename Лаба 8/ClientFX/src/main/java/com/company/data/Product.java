package com.company.data;

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
    private final float manufactureCost;
    private final Organization manufacturer; //Поле не может быть null

    public Product(String name, Coordinates coordinates, long price, String partNumber, UnitOfMeasure unitOfMeasure, float manufactureCost, Organization manufacturer) {
        this.userId = -1;
        this.id = -1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = null;
        this.price = price;
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
        this.manufactureCost = manufactureCost;
        this.manufacturer = manufacturer;
    }

    ////////////////////////////////////////////////////////////////////////


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
