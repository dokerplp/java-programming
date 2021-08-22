package com.company.data;

import java.io.Serializable;

/**
 * This class has information about company address
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Street, its getter and setter methods
     */
    private final String street; //Поле не может быть null
    /**
     * Zip Codem its getter and setter methods
     */
    private final String zipCode; //Длина строки не должна быть больше 12, Поле может быть null

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    ////////////////////////////////////////////////////////////////////////////

    public String getZipCode() {
        return zipCode;
    }

}
