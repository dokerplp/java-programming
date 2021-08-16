package com.company.data;


import com.company.interfaces.CallBack;
import com.company.memory.Registers;
import com.company.utility.nonstaticUtil.ProductOperator;

import java.io.Serializable;

/**
 * This class has information about product coordinates
 */
public class Coordinates implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////

    private final float x;
    private final Integer y; //Максимальное значение поля: 125, Поле не может быть null

    /**
     * Constructor for add command
     *
     * @param operator  - utility for creating new product
     * @param registers - trigger for human mode or auto mode
     */
    public Coordinates(ProductOperator operator, Registers registers) {
        if (registers.isMode()) System.out.println("Координаты: ");
        this.x = operator.X();
        this.y = operator.Y();
    }

    /**
     * Constructor for update command
     *
     * @param operator    - utility for creating new product
     * @param trigger     - human mode or auto mode
     *                    //* @param data - utility for taking client request
     * @param coordinates - last coordinates
     */
    public Coordinates(ProductOperator operator, boolean trigger, Coordinates coordinates, CallBack callBack) {

        String now = "Текущее значение поля ";

        if (trigger) System.out.println("Координаты: ");

        if (trigger) System.out.println(now + "\"Координата X\": " + coordinates.getX());
        if (callBack.callback()) this.x = operator.X();
        else this.x = coordinates.getX();

        if (trigger) System.out.println(now + "\"Координата Y\": " + coordinates.getY());
        if (callBack.callback()) this.y = operator.Y();
        else this.y = coordinates.getY();
    }

    ////////////////////////////////////////////////////////////////////////

    public float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
