package com.company.utility.database;

import com.company.data.*;
import com.company.utility.Log;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SQL {

    private final Log log = new Log();

    private final Connection connection;

    public SQL(Connection connection) {
        this.connection = connection;
    }

    public Product insertProduct(Product product, int id) {
        try {

            Function<String, UnitOfMeasure> uof = (arg) -> {
                switch (arg) {
                    case "METERS":
                        return UnitOfMeasure.METERS;
                    case "CENTIMETERS":
                        return UnitOfMeasure.CENTIMETERS;
                    case "LITERS":
                        return UnitOfMeasure.LITERS;
                    default:
                        return null;
                }
            };
            Function<String, OrganizationType> ot = (arg) -> {
                switch (arg) {
                    case "PUBLIC":
                        return OrganizationType.PUBLIC;
                    case "GOVERNMENT":
                        return OrganizationType.GOVERNMENT;
                    case "PRIVATE_LIMITED_COMPANY":
                        return OrganizationType.PRIVATE_LIMITED_COMPANY;
                    default:
                        return null;
                }
            };

            PreparedStatement statement = connection.prepareStatement(ScriptsSQL.INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, id);
            statement.setString(2, product.getName());
            statement.setLong(3, product.getPrice());
            statement.setString(4, product.getPartNumber());
            statement.setFloat(5, product.getManufactureCost());
            statement.setString(6, product.getUnitOfMeasure().toString());

            statement.execute();
            ResultSet pr = statement.getGeneratedKeys();
            pr.next();

            statement = connection.prepareStatement(ScriptsSQL.INSERT_COORDINATES, Statement.RETURN_GENERATED_KEYS);

            statement.setFloat(1, product.getCoordinates().getX());
            statement.setInt(2, product.getCoordinates().getY());

            statement.execute();
            ResultSet coord = statement.getGeneratedKeys();
            coord.next();
            Coordinates coordinates = new Coordinates(
                    coord.getFloat("x"),
                    coord.getInt("y")
            );

            statement = connection.prepareStatement(ScriptsSQL.INSERT_ORGANIZATION, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, product.getManufacturer().getName());
            statement.setLong(2, product.getManufacturer().getEmployeesCount());
            statement.setString(3, product.getManufacturer().getType().toString());

            statement.execute();
            ResultSet org = statement.getGeneratedKeys();
            org.next();

            Address address = null;
            statement = connection.prepareStatement(ScriptsSQL.INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
            if (product.getManufacturer().getOfficialAddress() != null) {
                statement.setString(1, product.getManufacturer().getOfficialAddress().getStreet());
                statement.setString(2, product.getManufacturer().getOfficialAddress().getZipCode());

                statement.execute();
                ResultSet addr = statement.getGeneratedKeys();
                addr.next();
                address = new Address(
                        addr.getString("street"),
                        addr.getString("zipcode")
                );
            }

            Organization organization = new Organization(
                    org.getInt("id"),
                    org.getString("name"),
                    org.getLong("employees_count"),
                    ot.apply(org.getString("organization_type")),
                    address
            );

            return new Product(
                    pr.getInt("user_id"),
                    pr.getInt("id"),
                    pr.getString("name"),
                    coordinates,
                    pr.getTimestamp("creation_date").toLocalDateTime().atZone(ZoneId.of("Europe/Moscow")),
                    pr.getLong("price"),
                    pr.getString("part_number"),
                    pr.getFloat("manufacture_cost"),
                    uof.apply(pr.getString("unit_of_measure")),
                    organization
            );

        } catch (SQLException e) {
            log.exception(e);
        }
        return null;
    }

    public int insertUser(String LOGIN, String PASS) {
        try {
            PreparedStatement statement = connection.prepareStatement(ScriptsSQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, LOGIN);
            statement.setString(2, PASS);

            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getInt("id");

        } catch (SQLException e) {
            log.exception(e);
            return -1;
        }
    }

    public int userId(String LOGIN, String PASS) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT ID FROM users WHERE login = ? AND pass = ?;");
            statement.setString(1, LOGIN);
            statement.setString(2, PASS);

            ResultSet rs = statement.executeQuery();
            if (!rs.next()) return -1;
            return rs.getInt("id");
        } catch (SQLException e) {
            log.exception(e);
            return -1;
        }
    }

    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(ScriptsSQL.SELECT_ALL);

            Statement st = connection.createStatement();
            ResultSet rst = st.executeQuery(ScriptsSQL.SELECT_ALL_ADDRESS);

            Map<Integer, Address> map = convertAddress(rst);

            while (rs.next()) {
                Product p;
                if ((p = convert(rs, map)) != null) products.add(p);
            }
            return products;
        } catch (SQLException e) {
            log.exception(e);
            return null;
        }
    }

    private Map<Integer, Address> convertAddress(ResultSet set) {
        Map<Integer, Address> map = new HashMap<>();
        try {
            while (set.next()) {
                map.put(
                        set.getInt("organization_id"), new Address(
                                set.getString("street"),
                                set.getString("zipcode")
                        ));
            }
            return map;
        } catch (SQLException e) {
            return null;
        }
    }

    private Product convert(ResultSet rs, Map<Integer, Address> map) {
        try {

            Function<String, UnitOfMeasure> uof = (arg) -> {
                switch (arg) {
                    case "METERS":
                        return UnitOfMeasure.METERS;
                    case "CENTIMETERS":
                        return UnitOfMeasure.CENTIMETERS;
                    case "LITERS":
                        return UnitOfMeasure.LITERS;
                    default:
                        return null;
                }
            };
            Function<String, OrganizationType> ot = (arg) -> {
                switch (arg) {
                    case "PUBLIC":
                        return OrganizationType.PUBLIC;
                    case "GOVERNMENT":
                        return OrganizationType.GOVERNMENT;
                    case "PRIVATE_LIMITED_COMPANY":
                        return OrganizationType.PRIVATE_LIMITED_COMPANY;
                    default:
                        return null;
                }
            };
            Function<Integer, Address> ad = (key) -> {
                if (map == null) return null;
                else return map.getOrDefault(key, null);
            };

            try {
                return new Product(
                        rs.getInt("uid"),
                        rs.getInt("pid"),
                        rs.getString("pname"),
                        new Coordinates(
                                rs.getFloat("x"),
                                rs.getInt("y")
                        ),
                        rs.getTimestamp("date").toLocalDateTime().atZone(ZoneId.of("Europe/Moscow")),
                        rs.getLong("price"),
                        rs.getString("part"),
                        rs.getFloat("man"),
                        uof.apply(rs.getString("uom")),
                        new Organization(
                                rs.getInt("oid"),
                                rs.getString("oname"),
                                rs.getLong("ec"),
                                ot.apply(rs.getString("type")),
                                ad.apply(rs.getInt("oid"))
                        )
                );
            } catch (NullPointerException e) {
                return null;
            }
        } catch (SQLException e) {
            log.exception(e);
            return null;
        }
    }

    public boolean deleteByID(int prod, int user) {
        try {
            PreparedStatement st;
            st = connection.prepareStatement("DELETE FROM address WHERE organization_id = (SELECT id FROM organization WHERE product_id = ?)\n" +
                    "AND (SELECT user_id FROM product WHERE id = ?) = ?;");
            st.setInt(1, prod);
            st.setInt(2, prod);
            st.setInt(3, user);
            st.execute();

            st = connection.prepareStatement("DELETE FROM coordinates WHERE product_id = ?\n" +
                    "AND (SELECT user_id FROM product WHERE id = ?) = ?");
            st.setInt(1, prod);
            st.setInt(2, prod);
            st.setInt(3, user);
            st.executeUpdate();

            st = connection.prepareStatement("DELETE FROM organization WHERE product_id = ? AND (SELECT user_id FROM product WHERE id = ?) = ?;");
            st.setInt(1, prod);
            st.setInt(2, prod);
            st.setInt(3, user);
            st.executeUpdate();

            st = connection.prepareStatement("DELETE FROM product WHERE id = ? AND user_id = ?;");
            st.setInt(1, prod);
            st.setInt(2, user);
            st.executeUpdate();

            return true;
        } catch (SQLException e) {
            log.exception(e);
            return false;
        }
    }

    public boolean deleteAll(int id) {
        try {
            PreparedStatement st = connection.prepareStatement("SELECT id FROM product WHERE user_id = ?;");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int prod = rs.getInt("id");
                deleteByID(prod, id);
            }

            return true;
        } catch (SQLException e) {
            log.exception(e);
            return false;
        }
    }

    public boolean deleteHigher(long price, int user) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM product WHERE price  > ?;");
            ps.setLong(1, price);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                deleteByID(rs.getInt("id"), user);
            }
            return true;
        } catch (SQLException e) {
            log.exception(e);
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE product\n" +
                            "SET name = ?, price = ?, part_number = ?, manufacture_cost = ?,unit_of_measure = ?::\"unit_of_measure\"\n" +
                            "WHERE id = ?;");
            ps.setString(1, product.getName());
            ps.setLong(2, product.getPrice());
            ps.setString(3, product.getPartNumber());
            ps.setFloat(4, product.getManufactureCost());
            ps.setString(5, product.getUnitOfMeasure().toString());
            ps.setInt(6, product.getId());

            ps.executeUpdate();

            ps = connection.prepareStatement(
                    "UPDATE coordinates\n" +
                            "SET x = ?, y = ?\n" +
                            "WHERE product_id = ?;");
            ps.setFloat(1, product.getCoordinates().getX());
            ps.setInt(2, product.getCoordinates().getY());
            ps.setInt(3, product.getId());

            ps.executeUpdate();

            ps = connection.prepareStatement(
                    "UPDATE organization\n" +
                            "SET name = ?, employees_count = ?, organization_type = ?::\"organization_type\"\n" +
                            "WHERE product_id = ?;");
            ps.setString(1, product.getManufacturer().getName());
            ps.setLong(2, product.getManufacturer().getEmployeesCount());
            ps.setString(3, product.getManufacturer().getType().toString());
            ps.setInt(4, product.getId());

            ps.executeUpdate();

            ps = connection.prepareStatement(
                    "UPDATE address\n" +
                            "SET street = ?,zipcode = ?\n" +
                            "WHERE organization_id = (SELECT ID FROM organization WHERE product_id = ?);");
            ps.setString(1, product.getManufacturer().getOfficialAddress().getStreet());
            ps.setString(2, product.getManufacturer().getOfficialAddress().getZipCode());
            ps.setInt(3, product.getId());

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            log.exception(e);
            return false;
        }
    }
}
