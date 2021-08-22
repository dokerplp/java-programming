package com.company.exceptions;

/**
 * Throws if you have no rights for writing in file
 */
public class CantWriteException extends RuntimeException{
    public CantWriteException() {
        System.err.println("Нет прав для записи данного файла");
    }
}
