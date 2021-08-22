package com.company.data;


import com.company.memory.Registers;
import com.company.utility.staticUtil.ProductOperator;

import java.io.Serializable;

/**
 * This class has information about product coordinates
 */
public class Coordinates implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////

    private final float x;
    private final Integer y; //Максимальное значение поля: 125, Поле не может быть null

    ////////////////////////////////////////////////////////////////////////

    public Coordinates(Registers registers, ProductOperator operator) {
        if (registers.isMode()) System.out.println("Координаты: ");
        this.x = operator.X();
        this.y = operator.Y();
    }

    public Coordinates(Coordinates coordinates, Registers registers, ProductOperator operator) {

        boolean trigger = registers.isMode();
        String now = "Текущее значение поля ";

        if (trigger) System.out.println("Координаты: ");

        if (trigger) System.out.println(now + "\"Координата X\": " + coordinates.getX());
        if (Product.ask()) this.x = operator.X();
        else this.x = coordinates.getX();

        if (trigger) System.out.println(now + "\"Координата Y\": " + coordinates.getY());
        if (Product.ask()) this.y = operator.Y();
        else this.y = coordinates.getY();
    }

    public float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
