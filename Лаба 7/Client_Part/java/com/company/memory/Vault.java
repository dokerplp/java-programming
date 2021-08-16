package com.company.memory;

import com.company.exceptions.VaultOverflowException;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Runs client's requests queue
 */
public class Vault {

    private final Queue<String> requests = new ArrayDeque<>();

    /**
     * Add one element to queue
     *
     * @param line - element
     */
    public void pushOne(String line) {
        requests.add(line);
    }

    /**
     * Add list of elements to queue
     *
     * @param list - collection
     */
    public void pushAll(List<String> list) {
        if (requests.size() > 1000) throw new VaultOverflowException();
        requests.addAll(list);
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
        if (requests.size() > 100) throw new VaultOverflowException();
        return requests.poll();
    }

    public int getElementAmount() {
        return requests.size();
    }
}
