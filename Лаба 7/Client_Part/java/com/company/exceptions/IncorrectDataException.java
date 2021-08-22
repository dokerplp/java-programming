package com.company.exceptions;


/**
 * Throws if you entered wrong data
 */
public class IncorrectDataException extends RuntimeException {

    private final String logs;

    public IncorrectDataException(String logs) {
        this.logs = logs;
    }

    public void outLogs() {
        System.err.println("Ошибка данных " + logs);
    }
}
