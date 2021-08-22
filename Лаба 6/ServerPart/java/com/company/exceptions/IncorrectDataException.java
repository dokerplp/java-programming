package com.company.exceptions;

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String logs) {
        System.err.println("Ошибка данных " + logs);
    }

    public IncorrectDataException() {
        System.err.println("Ошибка данных ");
    }
}
