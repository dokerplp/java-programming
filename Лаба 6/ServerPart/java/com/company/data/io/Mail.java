package com.company.data.io;

import com.company.data.Product;

import java.io.Serializable;

/**
 * Objects of this class are got from clients
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;


    //Mail data
    private String commandName;
    private Product productArgument;
    private Integer intArgument;
    private Long longArgument;
    private Float floatArgument;
    private boolean connect;

    public boolean isConnect() {
        return connect;
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
