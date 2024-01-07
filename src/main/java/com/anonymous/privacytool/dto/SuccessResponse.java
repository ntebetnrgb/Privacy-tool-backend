package com.anonymous.privacytool.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse<T> extends GenericResponse<T> {

    public SuccessResponse() {
        this.message = "Success";
        this.errorCode = null;
        this.data = null;
    }

    public SuccessResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}