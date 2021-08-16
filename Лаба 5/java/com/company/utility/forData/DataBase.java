package com.company.utility.forData;

import com.company.data.Product;
import com.company.utility.forCommands.RequestsQueue;

import java.util.List;

/**
 * Contains collection
 */
public class DataBase {

    private final List<Product> products;
    private final RequestsQueue queue;
    private final Fields fields;

    private final DataOperator operator;

    /**
     * DataBase Constructor
     * @param products - collection of products
     * @param queue - collection of commands
     * @param fields - common program fields
     * @param operator - utility for getting new elements
     */
    public DataBase(List<Product> products, RequestsQueue queue, Fields fields, DataOperator operator){
        this.products = products;
        this.queue = queue;
        this.fields = fields;
        this.operator = operator;
    }

    /**
     * Products list getter
     * @return collection
     */
    public List<Product> getBase(){
        return products;
    }

    /**
     * Commands queue getter
     * @return collection
     */
    public RequestsQueue getQueue(){
        return queue;
    }

    /**
     * Fields getter
     * @return fields
     */
    public Fields getFields(){
        return fields;
    }

    /**
     * Data Operator getter
     * @return data operator
     */
    public DataOperator getOperator() {
        return operator;
    }

}
