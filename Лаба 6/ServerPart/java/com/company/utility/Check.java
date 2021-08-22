package com.company.utility;

import com.company.data.Product;

import java.util.List;

public class Check {
    /**
     * Check on null
     *
     * @param o - Any Object
     * @return true if object is not null and false if it is
     */
    public static boolean NotNullCheck(Object o) {
        return o != null;
    }

    /**
     * Check on zero
     *
     * @param a - int or long or float number
     * @return true if number is above zero and false if is not
     */
    public static boolean AboveZeroCheck(int a) {
        return a > 0;
    }

    public static boolean AboveZeroCheck(long a) {
        return a > 0;
    }

    /**
     * Check on line
     *
     * @param a - line
     * @return true if line isn't empty and false if it is
     */
    public static boolean NotEmptyLineCheck(String a) {
        return !a.equals("") && !a.trim().equals("");
    }

    /**
     * Ckeck on product id
     *
     * @param products collection
     * @param id       id value
     * @return true if id is unique and fasle if it is not
     */
    public static boolean UniqueProdIdCheck(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) return false;
        }
        return true;
    }

    /**
     * Ckeck on organization id
     *
     * @param products collection
     * @param id       id value
     * @return true if id is unique and fasle if it is not
     */
    public static boolean UniqueOrgIdCheck(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) return false;
        }
        return true;
    }
}
