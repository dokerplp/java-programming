package com.company.data;

import java.io.Serializable;

/**
 * Enum of possible organization types
 */
public enum OrganizationType implements Serializable {

    PUBLIC,
    GOVERNMENT,
    PRIVATE_LIMITED_COMPANY;

    private static final long serialVersionUID = 1L;

    public static OrganizationType type(String type){
        switch (type) {
            case "PUBLIC":
                return PUBLIC;
            case "GOVERNMENT":
                return GOVERNMENT;
            case "PRIVATE_LIMITED_COMPANY":
                return PRIVATE_LIMITED_COMPANY;
            default:
                return null;
        }
    }
}
