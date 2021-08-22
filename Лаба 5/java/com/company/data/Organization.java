package com.company.data;

import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataChecker;

import java.util.List;

/**
 * This class has information about product manufacturer
 */
public class Organization {

    ////////////////////////////////////////////////////////////////////////

    /**
     * id, its getter and setter
     */
    private int id = -1; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * name, its getter and setter
     */
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Employees count, its getter and setter
     */
    private long employeesCount = -1; //Значение поля должно быть больше 0
    public long getEmployeesCount() {
        return employeesCount;
    }
    public void setEmployeesCount(long employeesCount) {
        this.employeesCount = employeesCount;
    }

    /**
     * Organization type, its getter and setter
     */
    private OrganizationType type = null; //Поле не может быть null
    public OrganizationType getType() {
        return type;
    }
    public void setType(OrganizationType type) {
        this.type = type;
    }

    /**
     * Organization address, its getter and setter
     */
    private Address officialAddress = null; //Поле может быть null
    public Address getOfficialAddress() {
        return officialAddress;
    }
    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * This methods checks if the fields has right values
     * @return true if all fields are correct and false if not
     */
    public boolean correct(DataBase base){

        List<Product> products = base.getBase();

        if (! (DataChecker.AboveZeroCheck(id) && DataChecker.UniqueOrgIdCheck(products, id))) return false;
        if (!(DataChecker.NotNullCheck(name) && DataChecker.NotEmptyLineCheck(name))) return false;
        if (! DataChecker.AboveZeroCheck(employeesCount)) return false;
        if (! DataChecker.NotNullCheck(type)) return false;
        if (officialAddress != null){
            if (! officialAddress.correct()) return false;
        }
        if (id > base.getFields().getOrgLastId()) base.getFields().setOrgLastId(id);
        return true;

    }

}
