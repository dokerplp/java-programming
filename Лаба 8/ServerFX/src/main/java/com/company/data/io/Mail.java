package com.company.data.io;

import com.company.data.Product;

import java.io.Serializable;

/**
 * Objects of this class are got from clients
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String commandName;
    private Product productArgument;
    private Integer intArgument;
    private Long longArgument;
    private Float floatArgument;

    private String LOGIN;
    private String PASS;

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASS() {
        return PASS;
    }

    public String getCommandName() {
        return commandName;
    }

    public Product getProductArgument() {
        return productArgument;
    }

    public Integer getIntArgument() {
        return intArgument;
    }

    public Long getLongArgument() {
        return longArgument;
    }

    public Float getFloatArgument() {
        return floatArgument;
    }

}
