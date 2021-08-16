package com.company.data;

import com.company.utility.Check;

import java.io.Serializable;

/**
 * This class has information about product coordinates
 */
public class Coordinates implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////

    /**
     * x coordinate, its getter and setter
     */
    private float x = 0f;
    /**
     * y coordinate, its getter and setter
     */
    private Integer y = null; //Максимальное значение поля: 125, Поле не может быть null

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * This methods checks if the fields has right values
     *
     * @return true if all fields are correct and false if not
     */
    public boolean correct() {
        return Check.NotNullCheck(y) && y <= 125;
    }

}
