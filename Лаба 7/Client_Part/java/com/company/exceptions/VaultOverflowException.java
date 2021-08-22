package com.company.exceptions;

public class VaultOverflowException extends RuntimeException {

    public VaultOverflowException() {
        System.err.println("Too many commands in queue...\nExecute_script stopped");
    }

}
