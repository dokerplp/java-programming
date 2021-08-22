package com.company.exceptions;

/**
 * Throws if client forgot to input argument
 */
public class NeedArgumentException extends RuntimeException {
    public NeedArgumentException(String arg) {
        System.err.println("Не хватает аргумета (" + arg + ")");
    }
}
