package com.sxx.generator.constant;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @Description:通用异常
 */
public class DBException extends RuntimeException {
    public DBException() {
        super();
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause) {
        super(cause);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
