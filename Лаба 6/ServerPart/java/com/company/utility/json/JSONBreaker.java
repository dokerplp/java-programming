package com.company.utility.json;

import com.company.data.*;
import com.company.exceptions.IncorrectDataException;
import com.company.memory.Vault;
import com.company.utility.DataConverter;
import com.company.utility.FileOperator;
import com.company.utility.Log;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * Class for converting json file to Product Class
 */
public class JSONBreaker {

    private final Vault vault;
    private final Log log = new Log();

    public JSONBreaker(Vault vault) throws IOException {

        this.vault = vault;

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

        if (json.toString().isEmpty()) {
            System.out.println("Файл пустой");
        }
        else {
            JSONtoProduct(json.toString());
        }

    }

    /**
     * Method divides list of products into products objects
     *
     * @param json - file contents
     * @throws IOException if you have problem with your file
     */
    public void JSONtoProduct(String json) throws IOException {

        List<HashMap<String, Object>> list = new ObjectMapper().readValue(json, List.class);

        if (list.isEmpty()) {
            System.out.println("Файл пустой");
            return;
        }

        for (HashMap<String, Object> it : list) {

            try {
                Product product = transform(it);
                if (product.correct(vault)) vault.getCollection().add(product);
                else throw new IncorrectDataException();
            } catch (IncorrectDataException e) {
                System.out.println("Продукт не добпален - данные не прошли проверку");
                log.exception(e);
            } catch (Exception e) {
                System.out.println("Продукт не добпален - некорректные данные");
                log.exception(e);
            }

        }
    }


    private Product transform(HashMap<String, Object> it) throws RuntimeException {
        Product product = new Product();
        product.setId(DataConverter.StringToInt((String) it.get("id")));
        product.setName((String) it.get("name"));
        Coordinates coordinates = new Coordinates();
        HashMap<String, Object> op = (HashMap) it.get("coordinates");
        coordinates.setX(DataConverter.StringToFloat((String) op.get("x")));
        coordinates.setY(DataConverter.StringToInt((String) op.get("y")));
        product.setCoordinates(coordinates);
        product.setCreationDate(ZonedDateTime.parse((String) it.get("creationDate")));
        product.setPrice(DataConverter.StringToLong((String) it.get("price")));
        product.setPartNumber((String) it.get("partNumber"));
        product.setManufactureCost(DataConverter.StringToFloat((String) it.get("manufactureCost")));
        product.setUnitOfMeasure(UnitOfMeasure.reverse((String) it.get("unitOfMeasure")));
        Organization organization = new Organization();
        HashMap<String, Object> or = (HashMap) it.get("manufacturer");
        organization.setId(DataConverter.StringToInt((String) or.get("id")));
        organization.setName((String) or.get("name"));
        organization.setEmployeesCount(DataConverter.StringToInt((String) or.get("employeesCount")));
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
