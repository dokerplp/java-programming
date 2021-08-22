package com.company.data.io;

import java.io.Serializable;

public class Verification implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String LOGIN;
    private final String PASS;
    private final boolean MODE;

    public Verification(String LOGIN, String PASS, boolean MODE) {
        this.LOGIN = LOGIN;
        this.MODE = MODE;
        this.PASS = PASS;
    }

}
