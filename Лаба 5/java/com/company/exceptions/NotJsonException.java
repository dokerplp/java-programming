package com.company.exceptions;

/**
 * Throws if file isn't JSON file
 */
public class NotJsonException extends RuntimeException{

    public NotJsonException() {
        System.err.println("Данный файл имеет недопустимое разрешение");
    }
}
