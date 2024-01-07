package com.anonymous.privacytool.dto;


import org.springframework.http.HttpStatus;

public enum Errors {

    OK("200","OK", HttpStatus.OK);

    Errors(){

    }
    private String code;
    private String message;
    private HttpStatus httpStatus;

    Errors(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
