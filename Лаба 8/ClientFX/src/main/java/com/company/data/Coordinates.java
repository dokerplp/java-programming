package com.company.data;

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

    public Coordinates(float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
