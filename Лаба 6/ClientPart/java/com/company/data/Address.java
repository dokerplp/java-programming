package com.company.data;


import com.company.interfaces.CallBack;
import com.company.utility.nonstaticUtil.ProductOperator;

import java.io.Serializable;

/**
 * This class has information about company address
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////////

    private final String street; //Поле не может быть null
    private final String zipCode; //Длина строки не должна быть больше 12, Поле может быть null

    /**
     * Constructor for add command
     *
     * @param operator - utility for creating new product
     */
    public Address(ProductOperator operator) {
        this.street = operator.STREET();
        this.zipCode = operator.ZIP();
    }

    /**
     * Constructor for update command
     *
     * @param operator - utility for creating new product
     *                 //* @param data - utility for taking client request
     * @param address  - last address
     * @param trigger  - human mode or auto mode
     */
    public Address(ProductOperator operator, CallBack callBack, Address address, boolean trigger) {

        String now = "Текущее значение поля ";

        if (address == null) {
            this.street = operator.STREET();
            this.zipCode = operator.ZIP();
        } else {
            if (trigger) System.out.println(now + "\"Улица\": " + address.getStreet());
            if (callBack.callback()) this.street = operator.STREET();
            else this.street = address.getStreet();

            if (trigger) System.out.println(now + "\"Почтовый код\": " + address.getZipCode());
            if (callBack.callback()) this.zipCode = operator.ZIP();
            else this.zipCode = address.getZipCode();
        }
    }

////////////////////////////////////////////////////////////////////////////

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
}
