package com.company.data;

import com.company.utility.forData.DataChecker;

/**
 * This class has information about company address
 */
public class Address {

    ////////////////////////////////////////////////////////////////////////////

    /**
     *  Street, its getter and setter methods
     */
    private String street = null; //Поле не может быть null
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *  Zip Codem its getter and setter methods
     */
    private String zipCode = null; //Длина строки не должна быть больше 12, Поле может быть null
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * This methods checks if the fields has right values
     * @return true if all fields are correct and false if not
     */
    public boolean correct(){

        if (! DataChecker.NotNullCheck(street)) return false;
        return DataChecker.NotNullCheck(zipCode) && zipCode.length() <= 12;
    }
}
