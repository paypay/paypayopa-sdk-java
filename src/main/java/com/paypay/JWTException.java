package com.paypay;

import java.util.List;
import java.util.Map;

public class JWTException extends Exception {

    private int code = 0;
    public JWTException() {}

    public JWTException(Throwable throwable) {
        super(throwable);
    }

    public JWTException(String message) {
        super(message);
    }

    public JWTException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Get the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getCode() {
        return code;
    }

}
