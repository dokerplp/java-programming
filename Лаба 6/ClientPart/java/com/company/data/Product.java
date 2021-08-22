package com.company.data;

import com.company.interfaces.CallBack;
import com.company.memory.Registers;
import com.company.utility.nonstaticUtil.DataOperator;
import com.company.utility.nonstaticUtil.ProductOperator;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * This class has information about product
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////

    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final long price; //Значение поля должно быть больше 0
    private final String partNumber; //Поле может быть null
    private final UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private float manufactureCost;
    private Organization manufacturer; //Поле не может быть null

    /**
     * Constructor for add command
     *
     * @param operator  - utility for creating new product
     * @param registers - trigger for human mode or auto mode
     * @param data      - utility for taking client request
     */
    public Product(ProductOperator operator, Registers registers, DataOperator data) {
        this.id = -1;
        this.name = operator.NAME();
        this.coordinates = new Coordinates(operator, registers);
        this.creationDate = null;
        this.price = operator.PRICE();
        this.partNumber = operator.PART_NUMBER();
        this.manufactureCost = operator.COST();
        this.unitOfMeasure = operator.MEASURE();
        this.manufacturer = new Organization(operator, registers, data);
    }

    /**
     * Constructor for update command
     *
     * @param operator  - utility for creating new product
     * @param registers - trigger for human mode or auto mode
     * @param data      - utility for taking client request
     * @param product   - last product
     */
    public Product(ProductOperator operator, Registers registers, DataOperator data, Product product) {

        boolean trigger = registers.isMode();

        String now = "Текущее значение поля ";

        CallBack callBack = () -> Up(data);

        this.id = product.getId();

        if (trigger) System.out.println(now + "\"Название продукта\": " + product.getName());
        if (Up(data)) this.name = operator.NAME();
        else this.name = product.getName();

        if (trigger) System.out.println("Координаты");
        if (Up(data)) this.coordinates = new Coordinates(operator, trigger, product.getCoordinates(), callBack);
        else this.coordinates = product.getCoordinates();

        this.creationDate = product.getCreationDate();

        if (trigger) System.out.println(now + "\"Цена\": " + product.getPrice());
        if (Up(data)) this.price = operator.PRICE();
        else this.price = product.getPrice();

        if (trigger) System.out.println(now + "\"Артикул\": " + product.getPartNumber());
        if (Up(data)) this.partNumber = operator.PART_NUMBER();
        else this.partNumber = product.getPartNumber();

        if (trigger) System.out.println(now + "\"Цена производства\": " + product.getManufactureCost());
        if (Up(data)) this.manufactureCost = operator.COST();
        else this.manufacturer = product.getManufacturer();

        if (trigger) System.out.println(now + "\"Единица измерения\": " + product.getUnitOfMeasure().getType());
        if (Up(data)) this.unitOfMeasure = operator.MEASURE();
        else this.unitOfMeasure = product.getUnitOfMeasure();

        if (trigger) System.out.println("Организация");
        if (Up(data)) this.manufacturer = new Organization(operator, trigger, callBack, product.getManufacturer());
        else this.manufacturer = product.getManufacturer();
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

    ////////////////////////////////////////////////////////////////////////

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    /**
     * Function asks if client wants to update field
     *
     * @param data - utility for taking client request
     * @return true if client wants to update field, false if isn't
     */
    private boolean Up(DataOperator data) {
        System.out.println("Желаете обновить поле?\nПустое поле - нет, любой символ - да");
        String ans = data.getSTRING();
        return !(ans == null);
    }


}
