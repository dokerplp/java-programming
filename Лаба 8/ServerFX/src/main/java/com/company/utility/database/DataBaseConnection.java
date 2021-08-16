package com.company.utility.database;

import com.company.utility.Exit;
import com.company.utility.Log;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private final Log log = new Log();

    //private final String DB_ADDR;
    private final String DB_BASE;
    private final String DB_NAME;
    private final int DB_PORT;
    private final String DB_HOST;

    private final String SV_LOGIN;
    private final String SV_PASS;
    private final String SV_ADDR;

    private final int SV_PORT;

    private final int SSH_PORT;
    private final int FORWARDING_PORT;

    //TODO client cant reconnect without reloading server

    public DataBaseConnection() {
        try {

            //this.DB_ADDR = System.getenv("DB_ADDR");
            this.DB_PORT = Integer.parseInt(System.getenv("DB_PORT"));
            this.DB_HOST = System.getenv("DB_HOST");
            this.DB_BASE = System.getenv("DB_BASE");
            this.DB_NAME= System.getenv("DB_NAME");

            this.SV_LOGIN = System.getenv("SV_LOGIN");
            this.SV_PASS = System.getenv("SV_PASS");
            this.SV_PORT = Integer.parseInt(System.getenv("SV_PORT"));
            this.SV_ADDR = System.getenv("SV_ADDR");

            this.SSH_PORT = Integer.parseInt(System.getenv("SSH_PORT"));
            this.FORWARDING_PORT = Integer.parseInt(System.getenv("FORWARDING_PORT"));


            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(SV_LOGIN, SV_ADDR, SSH_PORT);
            session.setPassword(SV_PASS);
            session.setConfig(config);
            session.connect();
            //System.out.println("Connected");
            session.setPortForwardingL(FORWARDING_PORT, DB_HOST, DB_PORT);
            //System.out.println("Port Forwarded");
        } catch (Exception e) {
            System.err.println("Wrong format of env, please follow this format:\n" +
                    "For ssh tunnel:\n" +
                    "ssh -p SSH_PORT SV_LOGIN@SV_ADDR\n" +
                    "Server login: SV_LOGIN\nServer password: SV_PASS\n" +
                    "For database connection:\n" +
                    "DB_BASE + DB_HOST + \":\" + FORWARDING_PORT + \"/\" + DB_NAME\n" +
                    "FORWARDING_PORT - FORWARDING PORT"
            );
            throw new Exit();
        }
    }

    public Connection makeConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_BASE + "localhost:" + FORWARDING_PORT + "/" + DB_NAME, SV_LOGIN, SV_PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found");
            log.exception(e);
            throw new Exit();
        } catch (SQLException e) {
            System.err.println("Connection to database failed");
            log.exception(e);
            throw new Exit();
        }
    }
}
