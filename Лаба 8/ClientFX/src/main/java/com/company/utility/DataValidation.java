package com.company.utility;

import com.company.data.*;
import com.company.exceptions.IncorrectDataException;

public class DataValidation {
    public static Object validateAddress(String street, String zipCode){
        boolean ok = true;
        StringBuilder s = new StringBuilder();
        if (street == null || street.equals("")) {
            ok = false;
            s.append("\nstreet - not empty field");
        }
        if (zipCode == null || zipCode.length() > 12) {
            ok = false;
            s.append("\nzipCode - empty or not more than 12 symbols");
        }
        if (ok) return new Address(street, zipCode);
        else return s.toString();
    }

    public static Object validateCoordinates(String x, String y){
        boolean ok = true;
        StringBuilder s = new StringBuilder();
        float X = 0;
        int Y = 0;
        try {
            X = DataConverter.StringToFloat(x);
        } catch (IncorrectDataException e) {
            ok = false;
            s.append("\nWrong format of X coordinate");
        }
        try {
            Y = DataConverter.StringToInt(y);
            if (Y > 125) {
                ok = false;
                s.append("\nY must be less than 125");
            }
        } catch (IncorrectDataException e) {
            ok = false;
            s.append("\nWrong format of Y coordinate");
        }
        if (ok) return new Coordinates(X, Y);
        else return s.toString();
    }
    public static Object validateOrganization(String name, String  employeesCount, OrganizationType type, Address officialAddress){
        boolean ok = true;
        StringBuilder s = new StringBuilder();
        long EC = 0;
        if (name == null || name.equals("")) {
            ok = false;
            s.append("\nname - not empty field");
        }
        try {
            EC = DataConverter.StringToLong(employeesCount);
        } catch (IncorrectDataException e) {
            ok = false;
            s.append("\nWrong format of employees count");
        }

        if (ok) return new Organization(name, EC, type, officialAddress);
        else return s.toString();
    }
    public static Object validateProduct(String name, Coordinates coordinates, String price, String partNumber, UnitOfMeasure unitOfMeasure, String manufactureCost, Organization manufacturer){
        boolean ok = true;
        StringBuilder s = new StringBuilder();
        long PRICE = 0;
        float MC = 0;
        if (name == null || name.equals("")) {
            ok = false;
            s.append("\nname - not empty field");
        }
        try {
            PRICE = DataConverter.StringToLong(price);
            if (PRICE < 0 || PRICE == 0){
                ok = false;
                s.append("\nPrice must be higher than 0");
            }
        } catch (IncorrectDataException e){
            ok = false;
            s.append("\nWrong format of price");
        }
        try {
            MC = DataConverter.StringToFloat(manufactureCost);
            if (MC < 0){
                ok = false;
                s.append("\nManufacture cost must be not less than 0");
            }
        } catch (IncorrectDataException e){
            ok = false;
            s.append("\nWrong format of price");
        }

        if (ok) return new Product(name, coordinates, PRICE, partNumber, unitOfMeasure, MC, manufacturer);
        else return s.toString();
    }
}
