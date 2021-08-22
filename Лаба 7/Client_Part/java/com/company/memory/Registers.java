package com.company.memory;

import com.company.data.Product;
import com.company.exceptions.InfinityRecursionException;

/**
 * Condition registers of client app
 */
public class Registers {

    //true - human mode, false - autoMode
    private boolean mode = true;
    //recursion register
    private int recursions = 0;
    private boolean updateTrigger = true;
    private Product update = null;

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

    public void switchUpdate() {
        updateTrigger = !updateTrigger;
    }

    public boolean getUpdateTrigger() {
        return updateTrigger;
    }

    public Product getUpdate() {
        return update;
    }

    public void setUpdate(Product update) {
        this.update = update;
    }
}
