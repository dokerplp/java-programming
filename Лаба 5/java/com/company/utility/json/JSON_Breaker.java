package com.company.utility.json;
import java.io.IOException;
import java.nio.file.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.io.*;

import com.company.data.*;
import com.company.utility.forCommands.FileOperator;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataOperator;
import com.company.exceptions.IncorrectDataException;
import com.fasterxml.jackson.databind.*;


/**
 * Class for converting json file to Product Class
 */
public class JSON_Breaker {

    private final DataBase base;
    private final List<Product> products;

    /**
     * Constructor
     * @param base - collection
     * @throws IOException if you have problems with file
     */
    public JSON_Breaker(DataBase base) throws IOException {

        this.base = base;

        products = base.getBase();

        String path = System.getenv("LAB");
        FileOperator.EnvExist(path);
        File DJEYSON = new File(path);
        FileOperator.FileExist(DJEYSON);
        FileOperator.IsFile(DJEYSON);
        FileOperator.IsJSON(path);
        FileOperator.Readable(DJEYSON);

        Path newPath = Paths.get(path);
        Scanner scanner = new Scanner(newPath);
        StringBuilder json = new StringBuilder();
        while (scanner.hasNext()) json.append(scanner.next());

        JSONtoProduct(json.toString());

    }

    /**
     * Method divides list of products into products objects
     * @param json - file contents
     * @throws IOException if you have problem with your file
     */
    public void JSONtoProduct(String json) throws IOException {

        List<HashMap<String, Object>> list = new ObjectMapper().readValue(json, List.class);
        DataOperator operator = new DataOperator(base.getQueue());

        for (HashMap<String, Object> it : list){

            try {
                Product product = transform(it, operator);
                if (product.correct(base)) products.add(product);
                else throw new IncorrectDataException();
            } catch (IncorrectDataException e){
                System.out.println("Продукт не добпален - данные не прошли проверку");
            } catch (Exception e) {
                System.out.println("Продукт не добпален - некорректные данные");
            }

        }
    }

    /**
     * Transform json object into Product object
     * @param it - Map of Product fields
     * @param operator - data operator
     * @return done Product Object ot Exception
     * @throws RuntimeException if something went wrong
     */
    private Product transform(HashMap<String, Object> it, DataOperator operator) throws RuntimeException{
        Product product = new Product();
        product.setId(operator.StringToInt((String) it.get("id")));
        product.setName((String) it.get("name"));
        Coordinates coordinates = new Coordinates();
        HashMap<String, Object> op = (HashMap) it.get("coordinates");
        coordinates.setX(operator.StringToFloat((String) op.get("x")));
        coordinates.setY(operator.StringToInt((String) op.get("y")));
        product.setCoordinates(coordinates);
        product.setCreationDate(ZonedDateTime.parse((String)it.get("creationDate")));
        product.setPrice(operator.StringToLong((String) it.get("price")));
        product.setPartNumber((String) it.get("partNumber"));
        product.setManufactureCost(operator.StringToFloat((String) it.get("manufactureCost")));
        product.setUnitOfMeasure(UnitOfMeasure.reverse((String) it.get("unitOfMeasure")));
        Organization organization = new Organization();
        HashMap<String, Object> or = (HashMap) it.get("manufacturer");
        organization.setId(operator.StringToInt((String) or.get("id")));
        organization.setName((String) or.get("name"));
        organization.setEmployeesCount(operator.StringToInt((String) or.get("employeesCount")));
        organization.setType(OrganizationType.reverse((String) or.get("type")));
        Address address = new Address();
        HashMap<String, Object> ad = (HashMap) or.get("address");
        if (ad.size() == 0) address = null;
        else {
            address.setStreet((String) ad.get("street"));
            address.setZipCode((String) ad.get("zipCode"));
        }
        organization.setOfficialAddress(address);
        product.setManufacturer(organization);
        return product;
    }



}
