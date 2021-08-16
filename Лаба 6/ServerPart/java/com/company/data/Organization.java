package com.company.data;

import com.company.memory.Vault;
import com.company.utility.Check;

import java.io.Serializable;
import java.util.List;

/**
 * This class has information about product manufacturer
 */
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    ////////////////////////////////////////////////////////////////////////

    /**
     * id, its getter and setter
     */
    private int id = -1; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * name, its getter and setter
     */
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    /**
     * Employees count, its getter and setter
     */
    private long employeesCount = -1; //Значение поля должно быть больше 0
    /**
     * Organization type, its getter and setter
     */
    private OrganizationType type = null; //Поле не может быть null
    /**
     * Organization address, its getter and setter
     */
    private Address officialAddress = null; //Поле может быть null

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * This methods checks if the fields has right values
     *
     * @return true if all fields are correct and false if not
     */
    public boolean correct(Vault vault) {

        List<Product> products = vault.getCollection();

        if (!(Check.AboveZeroCheck(id) && Check.UniqueOrgIdCheck(products, id))) return false;
        if (!(Check.NotNullCheck(name) && Check.NotEmptyLineCheck(name))) return false;
        if (!Check.AboveZeroCheck(employeesCount)) return false;
        if (!Check.NotNullCheck(type)) return false;
        if (officialAddress != null) {
            if (!officialAddress.correct()) return false;
        }
        if (id > vault.getRegisters().getOrgLastId()) vault.getRegisters().setOrgLastId(id);
        return true;

    }

}
