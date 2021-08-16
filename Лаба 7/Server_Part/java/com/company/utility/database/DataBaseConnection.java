package com.company.utility.database;

import com.company.utility.Exit;
import com.company.utility.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private final Log log = new Log();

    private final String PASSWORD;
    private final String LOGIN;
    private final String ADDRESS;

    public DataBaseConnection() {
        try {
            this.PASSWORD = System.getenv("PASSWORD");
            this.LOGIN = System.getenv("LOGIN");
            this.ADDRESS = System.getenv("ADDRESS");

            DriverManager.getConnection(ADDRESS, LOGIN, PASSWORD);
        } catch (Exception e) {
            System.err.println(
                    "Server didn't found environmental variables:" +
                            "\nADDRESS - database address\n" +
                            "\nLOGIN - login to database\n" +
                            "\nPASSWORD - password to database\n"
            );
            throw new Exit();
        }
    }

    public Connection makeConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://pg:5432/studs", LOGIN, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found");
            log.exception(e);
            throw new Exit();
        } catch (SQLException e) {
            System.err.println("Connection to database failed");
            log.exception(e);
        }
        return null;
    }
}
