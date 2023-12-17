package com.anonymous.privacytool.service;

import com.anonymous.privacytool.dto.GenericResponse;
import com.anonymous.privacytool.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    void processForgotPassword(String email);

    ResponseEntity<GenericResponse> signUpUser(User user) throws Exception;

    Optional<User> getUser(String  email);

    ResponseEntity<GenericResponse> verifyUserLogin(User user) throws Exception;

}
