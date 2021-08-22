package com.company.data;

import java.io.Serializable;

/**
 * Enum of possible organization types
 */
public enum OrganizationType implements Serializable {

    PUBLIC("Общественная"),
    GOVERNMENT("Правительственная"),
    PRIVATE_LIMITED_COMPANY("ЗАО");

    private static final long serialVersionUID = 1L;


    private final String type;

    /**
     * Constructor
     *
     * @param type - russian name
     */
    OrganizationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
