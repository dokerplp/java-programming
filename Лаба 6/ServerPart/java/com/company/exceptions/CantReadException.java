package com.company.exceptions;

/**
 * Throws if you have no rights for reading file
 */
public class CantReadException extends RuntimeException {

    public CantReadException() {
        System.err.println("Нет прав для чтения данного файла");
    }

}
