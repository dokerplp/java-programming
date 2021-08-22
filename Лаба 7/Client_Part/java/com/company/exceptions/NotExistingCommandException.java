package com.company.exceptions;

/**
 * Throws if client wrote non-existent command
 */
public class NotExistingCommandException extends RuntimeException {
    public NotExistingCommandException() {
        System.err.println("Указанной комманды не существует");
    }
}
