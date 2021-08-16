package com.company.exceptions;

/**
 * Throws if file doesn't exist
 */
public class FileDoesNotExistException extends RuntimeException {

    public FileDoesNotExistException() {
        System.err.println("Файла, находящегося по указанному адресу, не существует");
    }

}

