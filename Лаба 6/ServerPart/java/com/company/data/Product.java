package com.company.data;

import com.company.memory.Vault;
import com.company.utility.Check;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * This class has information about product
 */
public class Product implements Comparable<Product>, Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////

    /**
     * id, its getter and setter
     */
    private int id = -1; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * name, its getter and setter
     */
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    /**
     * coordinates, irs getter and setter
     */
    private Coordinates coordinates = null; //Поле не может быть null
    /**
     * creation date, its getter and setter
     */
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * price, its getter and setter
     */
    private long price = -1; //Значение поля должно быть больше 0
    /**
     * part number, its getter and setter
     */
    private String partNumber = null; //Поле может быть null
    /**
     * Manufacture cost, its getter and setter
     */
    private float manufactureCost;
    /**
     * Unit of measure, its getter and setter
     */
    private UnitOfMeasure unitOfMeasure = null; //Поле не может быть null
    /**
     * Manufacture cost, its getter and setter
     */
    private Organization manufacturer = null; //Поле не может быть null

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void newCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public float getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(float manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * This methods checks if the fields has right values
     *
     * @return true if all fields are correct and false if not
     */
    public boolean correct(Vault vault) {

        List<Product> list = vault.getCollection();

        if (!(Check.UniqueProdIdCheck(list, id) && Check.AboveZeroCheck(id))) return false;
        if (!(Check.NotNullCheck(name) && Check.NotEmptyLineCheck(name))) return false;
        if (!(coordinates.correct())) return false;
        if (!Check.NotNullCheck(creationDate)) return false;
        if (!Check.AboveZeroCheck(price)) return false;
        if (!Check.NotNullCheck(unitOfMeasure)) return false;
        if (!manufacturer.correct(vault)) return false;
        else {
            if (id > vault.getRegisters().getProdLastId()) vault.getRegisters().setProdLastId(id);
            return true;
        }

    }

    /**
     * This method realize List sort by price
     *
     * @param o - Another product
     * @return difference
     */
    @Override
    public int compareTo(Product o) {

        return (int) (getPrice() - o.getPrice());
    }
}
