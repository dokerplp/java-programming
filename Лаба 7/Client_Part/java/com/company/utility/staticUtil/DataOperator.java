package com.company.utility.staticUtil;

import com.company.exceptions.VaultOverflowException;
import com.company.memory.Vault;
import com.company.utility.Input;

/**
 * Utility for taken data from vault
 */
public class DataOperator {

    private static final Input.ScannerHandler SCANNER = Input.SCANNER;
    private final Vault vault;

    public DataOperator(Vault vault) {
        this.vault = vault;
    }

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

    public String getSTRING() {
        try {
            if (vault.getElementAmount() == 0) vault.pushOne(SCANNER.nextLine());
            String data = vault.pollOne();
            if (data.trim().equals("") || data.trim().equals("null")) return null;
            else return data;
        } catch (VaultOverflowException ignored) {
            return null;
        }
    }
}
