package com.anonymous.privacytool.dto;

import org.springframework.http.HttpStatus;

public class GenericResponse<T> {
    protected String message;
    protected String errorCode;
    protected T data;
}
