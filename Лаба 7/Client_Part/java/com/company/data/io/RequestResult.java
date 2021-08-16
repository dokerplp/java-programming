package com.company.data.io;

import java.io.Serializable;

public enum RequestResult implements Serializable {
    REQUEST_COMPLETED_SUCCESSFULLY,
    REQUEST_FAILED,
    NEED_RECONNECTION
}
