package com.anonymous.privacytool.dto;

import org.springframework.http.HttpStatus;

public class ApiError {
    private final HttpStatus status;
    private final String message;
    private final String errorCode;

    public ApiError(HttpStatus status, String message, String errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}