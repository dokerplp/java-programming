package com.company.exceptions;


/**
 * Throws if you entered wrong data
 */
public class IncorrectDataException extends RuntimeException{

    private final String logs;

    public IncorrectDataException(String logs){
        this.logs = logs;
        System.err.println("Ошибка данных... " + logs);
    }
    public IncorrectDataException(){
        this.logs = "";
        System.err.println("Ошибка данных...");
    }

    @Override
    public String toString(){
        return "Ошибка данных... " + logs;
    }
}
