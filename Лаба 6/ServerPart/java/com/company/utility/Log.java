package com.company.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;
import java.time.ZonedDateTime;

public class Log {

    private final Logger logger = LogManager.getLogger(Logger.class);

    public void turnOn(Integer port) {
        logger.info("Server started working on port {} at {} \n", port, ZonedDateTime.now());
    }

    public void turnOff(String reason) {
        logger.info("Server stopped working on reason {} at {} \n", reason, ZonedDateTime.now());
    }

    public void fatalTurnOff(Throwable err) {
        logger.fatal("Server is shut down at {} \n", ZonedDateTime.now(), err);
    }

    public void exception(Throwable err) {
        logger.error("Server caught new exception at {} \n", ZonedDateTime.now(), err);
    }

    public void newConnection(SocketAddress remoteAddress) {
        logger.info("Server got new connection from address {} at {} \n", remoteAddress, ZonedDateTime.now());
    }

    public void newRequest(String command, SocketAddress remoteAddress) {
        logger.info("Server got new request from {} - command {} at {} \n", remoteAddress, command, ZonedDateTime.now());
    }

    public void newAnswer(SocketAddress remoteAddress) {
        logger.info("Server sent new answer to client on address {} at {} \n", remoteAddress, ZonedDateTime.now());
    }

}
