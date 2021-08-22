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

    /**
     * Method matches russian name to the enum object
     *
     * @param measure - String of russian name
     * @return Organization type or null
     */
    public static UnitOfMeasure reverse(String measure) {
        switch (measure) {
            case "Сантиметры":
                return UnitOfMeasure.CENTIMETERS;
            case "Метры":
                return UnitOfMeasure.METERS;
            case "Литры":
                return UnitOfMeasure.LITERS;
            default:
                return null;
        }
    }

    /**
     * @return how it calls in Russian
     */
    public String getType() {
        return type;
    }

}
