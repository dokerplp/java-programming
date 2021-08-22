package com.company.utility.forData;

import com.company.exceptions.IncorrectDataException;
import com.company.utility.forCommands.RequestsQueue;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Scanning float, int, long and string
 * Making int, float and long from string
 */
public class DataOperator {

    private final Scanner console = new Scanner(System.in);
    private final RequestsQueue queue;

    /**
     * DataOperator Constructor
     * @param queue - collection
     */
    public DataOperator(RequestsQueue queue) {
        this.queue = queue;
    }

    /**
     * Get float value
     * @return float
     */
    public Float getFLOAT(){
        float data;
        String line = getSTRING();
        if (line == null) return null;
        else{
            try {
                data = Float.parseFloat(line);
                return data;
            } catch (Exception e){
                return null;
            }
        }
    }
    /**
     * Get int value
     * @return int
     */
    public Integer getINT(){
        int data;
        String line = getSTRING();
        if (line == null) return null;
        else{
            try {
                data = Integer.parseInt(line);
                return data;
            } catch (Exception e){
                return null;
            }
        }
    }
    /**
     * Get long value
     * @return long
     */
    public Long getLONG(){
        long data;
        String line = getSTRING();
        if (line == null) return null;
        else{
            try {
                data = Long.parseLong(line);
                return data;
            } catch (Exception e){
                return null;
            }
        }
    }
    /**
     * Get string value
     * @return string
     */
    public String getSTRING(){
        try{
            if (queue.getElementAmount() == 0) queue.pushOne(console.nextLine());
            String data = queue.pollOne();
            if (data.trim().equals("") || data.trim().equals("null")) return null;
            else return data;
        } catch (NoSuchElementException e){
            System.out.println("КЧАУ");
            System.exit(0);
        }
        return null;
    }

    /**
     * Making int from string
     * @param line - line of digits
     * @return int
     * @throws IncorrectDataException if line is not line of digits
     */
    public Integer StringToInt(String line) throws IncorrectDataException {
        try {
            return Integer.parseInt(line);
        } catch (Exception e){
            throw new IncorrectDataException("Неверный тип аргумента");
        }
    }
    /**
     * Making float from string
     * @param line - line of digits
     * @return float
     * @throws IncorrectDataException if line is not line of digits
     */
    public Float StringToFloat(String line) throws IncorrectDataException{
        try {
            return Float.parseFloat(line);
        } catch (Exception e){
            throw new IncorrectDataException("Неверный тип аргумента");
        }
    }
    /**
     * Making long from string
     * @param line - line of digits
     * @return long
     * @throws IncorrectDataException if line is not line of digits
     */
    public Long StringToLong(String line) throws IncorrectDataException{
        try {
            return Long.parseLong(line);
        } catch (Exception e){
            throw new IncorrectDataException("Неверный тип аргумента");
        }
    }
}
