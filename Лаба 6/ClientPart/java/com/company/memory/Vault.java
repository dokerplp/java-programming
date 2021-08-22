package com.company.memory;

import com.company.exceptions.VaultOverflowException;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Runs client's requests queue
 */
public class Vault {

    //Requests
    private final Queue<String> requests = new ArrayDeque<>();
    /**
     * Amount of elements and its getter
     */
    private int ElementAmount = 0;

    /**
     * Add one element to queue
     *
     * @param line - element
     */
    public void pushOne(String line) {
        requests.add(line);
        ElementAmount += 1;
    }

    /**
     * Add list of elements to queue
     *
     * @param list - collection
     */
    public void pushAll(List<String> list) {
        if (ElementAmount > 1000) throw new VaultOverflowException();
        requests.addAll(list);
        ElementAmount += list.size();
    }

    /**
     * Clear queue
     */
    public void clearAll() {
        requests.clear();
    }

    /**
     * Take one element from queue
     *
     * @return element
     */
    public String pollOne() {
        if (ElementAmount > 1000) throw new VaultOverflowException();
        ElementAmount -= 1;
        return requests.poll();
    }

    public int getElementAmount() {
        return ElementAmount;
    }
}
