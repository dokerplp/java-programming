package com.company.data.io;

import com.company.data.Product;

import java.io.Serializable;
import java.util.List;

/**
 * Server send objects of this class as answer
 */
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String answer;
    private final List<Product> products;
    private final RequestResult result;

    public Answer(String answer, List<Product> products, RequestResult result) {
        this.answer = answer;
        this.products = products;
        this.result = result;
    }

    public Answer(String answer, RequestResult result) {
        this.answer = answer;
        this.result = result;
        this.products = null;
    }
}
