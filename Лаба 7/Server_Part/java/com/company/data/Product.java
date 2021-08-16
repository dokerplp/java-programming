package com.company.data;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * This class has information about product
 */
public class Product implements Comparable<Product>, Serializable {

    private static final long serialVersionUID = 1L;

    private final int userId;
    /**
     * name, its getter and setter
     */
    private final String name; //Поле не может быть null, Строка не может быть пустой
    ////////////////////////////////////////////////////////////////////////
    /**
     * coordinates, irs getter and setter
     */
    private final Coordinates coordinates; //Поле не может быть null
    /**
     * creation date, its getter and setter
     */
    private final java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * price, its getter and setter
     */
    private final long price; //Значение поля должно быть больше 0
    /**
     * part number, its getter and setter
     */
    private final String partNumber; //Поле может быть null
    /**
     * Manufacture cost, its getter and setter
     */
    private final float manufactureCost;
    /**
     * Unit of measure, its getter and setter
     */
    private final UnitOfMeasure unitOfMeasure; //Поле не может быть null
    /**
     * Manufacture cost, its getter and setter
     */
    private final Organization manufacturer; //Поле не может быть null
    /**
     * id, its getter and setter
     */
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    public Product(int userId, int id, String name, Coordinates coordinates, ZonedDateTime creationDate, long price, String partNumber, float manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
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

    ////////////////////////////////////////////////////////////////////////

    public Organization getManufacturer() {
        return manufacturer;
    }

    @Override
    public int compareTo(Product o) {
        return (int) (getPrice() - o.getPrice());
    }
}
