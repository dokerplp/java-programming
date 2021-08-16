package com.company.memory;

import com.company.data.Product;

import java.util.List;

/**
 * Keeps collection and registers
 */
public class Vault {

    private final List<Product> collection;
    private final Registers registers;

    public Vault(List<Product> collection, Registers registers) {
        this.collection = collection;
        this.registers = registers;
    }

    public List<Product> getCollection() {
        return collection;
    }

    public Registers getRegisters() {
        return registers;
    }
}
