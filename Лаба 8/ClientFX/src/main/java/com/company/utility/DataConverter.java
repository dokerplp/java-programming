package com.company.utility;

import com.company.exceptions.IncorrectDataException;

/**
 * Utility for converting data
 */
public class DataConverter {
    /**
     * Making int from string
     *
     * @param line - line of digits
     * @return int
     * @throws IncorrectDataException if line is not line of digits
     */
    public static Integer StringToInt(String line) throws IncorrectDataException {
        try {
            return Integer.parseInt(line);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }

    /**
     * Making float from string
     *
     * @param line - line of digits
     * @return float
     * @throws IncorrectDataException if line is not line of digits
     */
    public static Float StringToFloat(String line) throws IncorrectDataException {
        try {
            return Float.parseFloat(line);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }

    /**
     * Making long from string
     *
     * @param line - line of digits
     * @return long
     * @throws IncorrectDataException if line is not line of digits
     */
    public static Long StringToLong(String line) throws IncorrectDataException {
        try {
            return Long.parseLong(line);
        } catch (Exception e) {
            throw new IncorrectDataException();
        }
    }
}
