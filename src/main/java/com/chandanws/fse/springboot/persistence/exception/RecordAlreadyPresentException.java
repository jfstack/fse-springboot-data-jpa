package com.chandanws.fse.springboot.persistence.exception;

public class RecordAlreadyPresentException extends RuntimeException {

    public RecordAlreadyPresentException(String message) {
        super(message);
    }

    public RecordAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
