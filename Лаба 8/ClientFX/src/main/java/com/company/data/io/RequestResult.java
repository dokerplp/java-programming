package com.company.data.io;

import java.io.Serializable;

public enum RequestResult implements Serializable {
    SUCCESS,
    ERROR,
    NEED_RECONNECTION
}
