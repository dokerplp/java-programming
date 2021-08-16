package com.company.utility.json;

import com.company.data.Product;

import java.util.List;

/**
 * Class makes json string from Product object
 */
public class JSON_Builder {

    /**
     * Making json from one product
     * @param product - Product object
     * @return json product
     */
    public String getJSON(Product product){
        String o = "     ";
        String file = "";
        file += o + "{\n";
        file += o + o + "\"id\": \"" + product.getId() + "\",\n";
        file += o + o + "\"name\": \"" + product.getName() + "\",\n";
        file += o + o + "\"coordinates\": {\n";
        file += o + o + o + "\"x\": \"" + product.getCoordinates().getX() + "\",\n";
        file += o + o + o + "\"y\": \"" + product.getCoordinates().getY() + "\"\n";
        file += o + o + "},\n";
        file += o + o + "\"creationDate\": \"" + product.getCreationDate() + "\",\n";
        file += o + o + "\"price\": \"" + product.getPrice() + "\",\n";
        file += o + o + "\"partNumber\": \"" + product.getPartNumber() + "\",\n";
        file += o + o + "\"manufactureCost\": \"" + product.getManufactureCost() + "\",\n";
        file += o + o + "\"unitOfMeasure\": \"" + product.getUnitOfMeasure().getType() + "\",\n";
        file += o + o + "\"manufacturer\": {\n";
        file += o + o + o + "\"id\": \"" + product.getManufacturer().getId() + "\",\n";
        file += o + o + o + "\"name\": \"" + product.getManufacturer().getName() + "\",\n";
        file += o + o + o + "\"employeesCount\": \"" + product.getManufacturer().getEmployeesCount() + "\",\n";
        file += o + o + o + "\"type\": \"" + product.getManufacturer().getType().getType() + "\",\n";
        if (product.getManufacturer().getOfficialAddress() == null) {
            file += o + o + o + "\"address\": {}\n";
        }
        else {
            file += o + o + o + "\"address\": {\n";
            file += o + o + o + o + "\"street\": \"" + product.getManufacturer().getOfficialAddress().getStreet() + "\",\n";
            file += o + o + o + o + "\"zipCode\": \"" + product.getManufacturer().getOfficialAddress().getZipCode() + "\"\n";
            file += o + o + o + "}\n";
        }
        file += o + o + "}\n";
        file += o + "}";

        return file;
    }

    /**
     * Making json string from Products collection
     * @param products - collection
     * @return done json string of all products
     */
    public String packCollection(List<Product> products){
        StringBuilder file = new StringBuilder();
        file.append("[\n");
        int counter = 0;
        for (Product product : products){
            file.append(getJSON(product));
            if (++counter != products.size()) file.append(",\n");
            else file.append("\n");
        }
        file.append("]\n");
        return file.toString();
    }

}
