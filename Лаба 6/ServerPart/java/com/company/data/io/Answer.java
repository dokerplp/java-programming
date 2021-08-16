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

    public Answer(String answer, List<Product> products) {
        this.answer = answer;
        this.products = products;
    }

    public Answer(String answer) {
        this.answer = answer;
        this.products = null;
    }

    public String getAnswer() {
        return answer;
    }
}
