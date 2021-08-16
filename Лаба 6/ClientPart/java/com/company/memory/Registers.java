package com.company.memory;

import com.company.exceptions.InfinityRecursionException;

/**
 * Condition registers of client app
 */
public class Registers {

    //true - human mode, false - autoMode
    private boolean mode = true;
    //recursion register
    private int recursions = 0;

    public boolean isMode() {
        return mode;
    }

    public void modeTrue() {
        mode = true;
    }

    public void modeFalse() {
        mode = false;
    }

    public void clearRecursion() {
        recursions = 0;
    }

    public void newRecursion() {
        if (++recursions > 20) throw new InfinityRecursionException();
    }

}
