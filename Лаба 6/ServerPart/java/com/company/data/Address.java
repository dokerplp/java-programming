package com.company.data;

import com.company.utility.Check;

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
    private String street = null; //Поле не может быть null
    /**
     * Zip Codem its getter and setter methods
     */
    private String zipCode = null; //Длина строки не должна быть больше 12, Поле может быть null

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * This methods checks if the fields has right values
     *
     * @return true if all fields are correct and false if not
     */
    public boolean correct() {
        if (!Check.NotNullCheck(street)) return false;
        return Check.NotNullCheck(zipCode) && zipCode.length() <= 12;
    }
}
