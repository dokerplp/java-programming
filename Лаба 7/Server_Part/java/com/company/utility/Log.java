package com.company.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZonedDateTime;

public class Log {

    private final Logger logger = LogManager.getLogger(Logger.class);

    public void turnOn(Integer port) {
        logger.info("Server started working on port {} at {}", port, ZonedDateTime.now());
    }

    public void turnOff(String reason) {
        logger.info("Server stopped working on reason {} at {}", reason, ZonedDateTime.now());
    }

    public void exception(Throwable err) {
        logger.error("Server caught new exception at {}", ZonedDateTime.now(), err);
    }

    public void disconnect(String login) {
        logger.info("{} left the server", login);
    }

    public void join(String login) {
        logger.info("{} joined the server", login);
    }

    public void newRequest(String command, String login) {
        logger.info("Server got new request from {} - command {} at {}", login, command, ZonedDateTime.now());
    }
}
