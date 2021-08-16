package com.company.data;

import java.io.Serializable;

/**
 * This class has information about product manufacturer
 */
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////

    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private final String name; //Поле не может быть null, Строка не может быть пустой

    private final long employeesCount; //Значение поля должно быть больше 0

    private final OrganizationType type; //Поле не может быть null

    private final Address officialAddress; //Поле может быть null

    ////////////////////////////////////////////////////////////////////////

    public Organization(int id, String name, long employeesCount, OrganizationType type, Address officialAddress) {
        this.id = id;
        this.name = name;
        this.employeesCount = employeesCount;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }
}
