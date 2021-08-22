package com.company.utility.database;

public class ScriptsSQL {
    public static final String INSERT_PRODUCT =
            "INSERT INTO product (USER_ID, NAME, PRICE, PART_NUMBER, MANUFACTURE_COST, UNIT_OF_MEASURE)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?::\"unit_of_measure\");";
    public static final String INSERT_COORDINATES =
            "INSERT INTO coordinates (PRODUCT_ID, X, Y)\n" +
                    "VALUES ((SELECT MAX(ID) FROM product), ?, ?);";
    public static final String INSERT_ORGANIZATION =
            "INSERT INTO organization (product_id, name, employees_count, organization_type)\n" +
                    "VALUES ((SELECT MAX(ID) FROM product), ?, ?, ?::\"organization_type\");";
    public static final String INSERT_ADDRESS =
            "INSERT INTO address (ORGANIZATION_ID, STREET, ZIPCODE)\n" +
                    "VALUES ((SELECT MAX(ID) FROM organization), ?, ?);";
    public static final String INSERT_USER =
            "INSERT INTO users (login, pass)\n" +
                    "VALUES (?, ?);";
    public static final String SELECT_ALL =
            "SELECT user_id as uid, login, pass, c.product_id as pid, p.name as pname, creation_date as date,\n" +
                    "       price, part_number as part, manufacture_cost as man, unit_of_measure as uom,x, y,\n" +
                    "       o.id as oid, o.name as oname, employees_count as ec, organization_type as type\n" +
                    "FROM users\n" +
                    "         right join product p on users.id = p.user_id\n" +
                    "         right join coordinates c on p.id = c.product_id\n" +
                    "         right join organization o on p.id = o.product_id\n";
    public static final String SELECT_ALL_ADDRESS = "SELECT * FROM ADDRESS";
}
