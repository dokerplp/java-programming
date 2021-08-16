package com.company.utility.staticUtil;

/**
 * Utility for checking data
 */
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
    public static boolean AboveZeroCheck(long a) {
        return a > 0;
    }

    public static boolean AboveZeroCheck(float a) {
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
}
