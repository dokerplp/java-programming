package com.company.utility.forCommands;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Utility for running queue
 */
public class RequestsQueue {

    private final Queue<String> request = new ArrayDeque<>();

    /**
     * Add one element to queue
     * @param line - element
     */
    public void pushOne(String line){
        request.add(line);
        ElementAmount += 1;
    }

    /**
     * Add list of elements to queue
     * @param list - collection
     */
    public void pushAll(List<String> list){
        request.addAll(list);
        ElementAmount += list.size();
    }

    /**
     * Clear queue
     */
    public void clearAll(){
        request.clear();
    }

    /**
     * Take one element from queue
     * @return element
     */
    public String pollOne(){
        ElementAmount -= 1;
        return request.poll();
    }

    /**
     * Amount of elements and its getter
     */
    private int ElementAmount = 0;
    public int getElementAmount() {
        return ElementAmount;
    }

    /**
     * Status of queue (true - user, false - script) and its getter and setter
     */
    private boolean Status = true;
    public boolean isStatus() {
        return Status;
    }
    public void setStatus(boolean status) {
        Status = status;
    }
}
