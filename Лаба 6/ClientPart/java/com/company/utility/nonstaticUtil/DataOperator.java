package com.company.utility.nonstaticUtil;

import com.company.exceptions.VaultOverflowException;
import com.company.memory.Vault;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Utility for taken data from vault
 */
public class DataOperator {

    private final Scanner console = new Scanner(System.in);
    private final Vault buffer;

    /**
     * DataOperator Constructor
     *
     * @param buffer - buffer
     */
    public DataOperator(Vault buffer) {
        this.buffer = buffer;
    }

    /**
     * Get float value
     *
     * @return float
     */
    public Float getFLOAT() {
        float data;
        String line = getSTRING();
        if (line == null) return null;
        else {
            try {
                data = Float.parseFloat(line);
                return data;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * Get int value
     *
     * @return int
     */
    public Integer getINT() {
        int data;
        String line = getSTRING();
        if (line == null) return null;
        else {
            try {
                data = Integer.parseInt(line);
                return data;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * Get long value
     *
     * @return long
     */
    public Long getLONG() {
        long data;
        String line = getSTRING();
        if (line == null) return null;
        else {
            try {
                data = Long.parseLong(line);
                return data;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /**
     * Get string value
     *
     * @return string
     */
    public String getSTRING() {
        try {
            if (buffer.getElementAmount() == 0) buffer.pushOne(console.nextLine());
            String data = buffer.pollOne();
            if (data.trim().equals("") || data.trim().equals("null")) return null;
            else return data;
        } catch (NoSuchElementException e) {
            System.out.println("КЧАУ");
            System.exit(0);
        } catch (VaultOverflowException ignored){}
        return null;
    }
}
