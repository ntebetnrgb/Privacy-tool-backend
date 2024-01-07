package com.anonymous.privacytool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenericResponse<T> {
    protected String message;
    protected String errorCode;
    protected T data;
}
