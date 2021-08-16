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
}
