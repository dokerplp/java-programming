package com.company.data;

import com.company.interfaces.CallBack;
import com.company.memory.Registers;
import com.company.utility.nonstaticUtil.DataOperator;
import com.company.utility.nonstaticUtil.ProductOperator;

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

    /**
     * Constructor for add command
     *
     * @param operator  - utility for creating new product
     * @param registers - trigger for human mode or auto mode
     * @param data      - utility for taking client request
     */
    public Organization(ProductOperator operator, Registers registers, DataOperator data) {
        if (registers.isMode()) System.out.println("Изготовитель: ");
        this.id = 0;
        this.name = operator.ORG_NAME();
        this.employeesCount = operator.E_COUNT();
        this.type = operator.TYPE();
        if (registers.isMode()) System.out.println("Желаете ввести адрес? Пустая строка - нет, любое поле - да");
        if (data.getSTRING() != null) this.officialAddress = new Address(operator);
        else this.officialAddress = null;
    }

    /**
     * Construction for update command
     *
     * @param operator     - utility for creating new product
     * @param trigger      - human mode or auto mode
     * @param callBack     - function Up() from Product class
     * @param organization - last organization
     */
    public Organization(ProductOperator operator, boolean trigger, CallBack callBack, Organization organization) {
        String now = "Текущее значение поля ";

        if (trigger) System.out.println("Изготовитель: ");

        this.id = organization.getId();

        if (trigger) System.out.println(now + "\"Название организации\": " + organization.getName());
        if (callBack.callback()) this.name = operator.ORG_NAME();
        else this.name = organization.getName();

        if (trigger) System.out.println(now + "\"Количество работников\": " + organization.getEmployeesCount());
        if (callBack.callback()) this.employeesCount = operator.E_COUNT();
        else this.employeesCount = organization.getEmployeesCount();

        if (trigger) System.out.println(now + "\"Тип компании\": " + organization.getType().getType());
        if (callBack.callback()) this.type = operator.TYPE();
        else this.type = organization.getType();

        if (trigger) System.out.println("Адрес");
        if (callBack.callback())
            this.officialAddress = new Address(operator, callBack, organization.getOfficialAddress(), trigger);
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
////////////////////////////////////////////////////////////////////////

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }
}
