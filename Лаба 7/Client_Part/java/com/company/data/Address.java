package com.company.data;

import com.company.memory.Registers;
import com.company.utility.staticUtil.ProductOperator;

import java.io.Serializable;

/**
 * This class has information about company address
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////////

    private final String street; //Поле не может быть null
    private final String zipCode; //Длина строки не должна быть больше 12, Поле может быть null

    ////////////////////////////////////////////////////////////////////////////

    public Address(ProductOperator operator) {
        this.street = operator.STREET();
        this.zipCode = operator.ZIP();
    }

    public Address(Address address, Registers registers, ProductOperator operator) {

        boolean trigger = registers.isMode();
        String now = "Текущее значение поля ";

        if (address == null) {
            this.street = operator.STREET();
            this.zipCode = operator.ZIP();
        } else {
            if (trigger) System.out.println(now + "\"Улица\": " + address.getStreet());
            if (Product.ask()) this.street = operator.STREET();
            else this.street = address.getStreet();

            if (trigger) System.out.println(now + "\"Почтовый код\": " + address.getZipCode());
            if (Product.ask()) this.zipCode = operator.ZIP();
            else this.zipCode = address.getZipCode();
        }
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
}
