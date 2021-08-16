package com.company.data;

import com.company.memory.Registers;
import com.company.utility.Input;
import com.company.utility.staticUtil.DataOperator;
import com.company.utility.staticUtil.ProductOperator;

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

    public Organization(Registers registers, ProductOperator operator, DataOperator data) {
        if (registers.isMode()) System.out.println("Изготовитель: ");
        this.id = 0;
        this.name = operator.ORG_NAME();
        this.employeesCount = operator.E_COUNT();
        this.type = operator.TYPE();
        if (registers.isMode())
            System.out.println("Желаете ввести адрес? Пустая строка - нет, любое поле - да");
        String ans = data.getSTRING();
        if (ans != null && !ans.equals("") && !ans.equals("null"))
            this.officialAddress = new Address(operator);
        else this.officialAddress = null;
    }

    public Organization(Organization organization, Registers registers, ProductOperator operator) {

        boolean trigger = registers.isMode();
        String now = "Текущее значение поля ";

        if (trigger) System.out.println("Изготовитель: ");

        this.id = organization.getId();

        if (trigger) System.out.println(now + "\"Название организации\": " + organization.getName());
        if (Product.ask()) this.name = operator.ORG_NAME();
        else this.name = organization.getName();

        if (trigger) System.out.println(now + "\"Количество работников\": " + organization.getEmployeesCount());
        if (Product.ask()) this.employeesCount = operator.E_COUNT();
        else this.employeesCount = organization.getEmployeesCount();

        if (trigger) System.out.println(now + "\"Тип компании\": " + organization.getType().toString());
        if (Product.ask()) this.type = operator.TYPE();
        else this.type = organization.getType();

        if (trigger) System.out.println("Адрес");
        if (Product.ask()) this.officialAddress = new Address(organization.getOfficialAddress(), registers, operator);
        else this.officialAddress = organization.getOfficialAddress();
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
