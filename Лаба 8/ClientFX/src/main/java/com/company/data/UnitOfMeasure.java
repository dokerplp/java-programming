package com.company.data;

import java.io.Serializable;

/**
 * Enum of possible units of measure
 */
public enum UnitOfMeasure implements Serializable {

    METERS,
    CENTIMETERS,
    LITERS;

    private static final long serialVersionUID = 1L;

    public static UnitOfMeasure type(String type){
        switch (type) {
            case "METERS":
                return METERS;
            case "CENTIMETERS":
                return CENTIMETERS;
            case "LITERS":
                return LITERS;
            default:
                return null;
        }
    }
}
