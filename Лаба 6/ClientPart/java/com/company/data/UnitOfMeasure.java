package com.company.data;

import java.io.Serializable;

/**
 * Enum of possible units of measure
 */
public enum UnitOfMeasure implements Serializable {

    METERS("Метры"),
    CENTIMETERS("Сантиметры"),
    LITERS("Литры");

    private static final long serialVersionUID = 1L;

    private final String type;

    /**
     * Constructor
     *
     * @param type - name
     */
    UnitOfMeasure(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
