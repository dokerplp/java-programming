package com.company.data.io;

import com.company.data.Product;

import java.io.Serializable;
import java.util.List;

/**
 * Client gets objects on this class
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String answer;

    private RequestResult result;

    private List<Product> products;

    public String getAnswer() {
        return answer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public RequestResult getResult() {
        return result;
    }
}

