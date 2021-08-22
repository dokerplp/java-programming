package com.company.data;

import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataChecker;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * This class has information about product
 */
public class Product implements Comparable<Product>{

    ////////////////////////////////////////////////////////////////////////

    /**
     * id, its getter and setter
     */
    private int id = -1; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * name, its getter and setter
     *
     */
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * coordinates, irs getter and setter
     */
    private Coordinates coordinates = null; //Поле не может быть null
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * creation date, its getter and setter
     */
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * Set date and time of creation
     */
    public void NewCreationDate(){
        creationDate = ZonedDateTime.now();
    }

    /**
     * price, its getter and setter
     */
    private long price = -1; //Значение поля должно быть больше 0
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }

    /**
     * part number, its getter and setter
     */
    private String partNumber = null; //Поле может быть null
    public String getPartNumber() {
        return partNumber;
    }
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Manufacture cost, its getter and setter
     */
    private float manufactureCost;
    public float getManufactureCost() {
        return manufactureCost;
    }
    public void setManufactureCost(float manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    /**
     * Unit of measure, its getter and setter
     */
    private UnitOfMeasure unitOfMeasure = null; //Поле не может быть null
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Manufacture cost, its getter and setter
     */
    private Organization manufacturer = null; //Поле не может быть null
    public Organization getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * This methods checks if the fields has right values
     * @return true if all fields are correct and false if not
     */
    public boolean correct (DataBase base){

        List<Product> list = base.getBase();

        if (!(DataChecker.UniqueProdIdCheck(list, id) && DataChecker.AboveZeroCheck(id))) return false;
        if (!(DataChecker.NotNullCheck(name) && DataChecker.NotEmptyLineCheck(name))) return false;
        if (! (coordinates.correct())) return false;
        if (! DataChecker.NotNullCheck(creationDate)) return false;
        if (! DataChecker.AboveZeroCheck(price)) return false;
        if (! DataChecker.NotNullCheck(unitOfMeasure)) return false;
        if (!manufacturer.correct(base)) return false;
        else {
            if (id > base.getFields().getProdLastId()) base.getFields().setProdLastId(id);
            return true;
        }

    }

    /**
     * This method realize List sort by price
     * @param o - Another product
     * @return difference
     */
    @Override
    public int compareTo(Product o) {
        return (int)(getPrice() - o.getPrice());
    }
}
