package com.anonymous.privacytool.controller;

import com.anonymous.privacytool.dto.ForgotPasswordRequest;
import com.anonymous.privacytool.dto.GenericResponse;
import com.anonymous.privacytool.dto.SuccessResponse;
import com.anonymous.privacytool.entity.User;
import com.anonymous.privacytool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/forgot-password")
    public ResponseEntity<GenericResponse<Object>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        userService.processForgotPassword(request.getEmail());
        return ResponseEntity.ok(new SuccessResponse<>());
    }

    @PostMapping("/signup")
    public ResponseEntity<GenericResponse> userSignUp(@RequestBody User user) throws Exception {
        return userService.signUpUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<Object>> userLogin(@RequestBody User user) throws Exception {
        userService.verifyUserLogin(user);
        return ResponseEntity.ok(new SuccessResponse<>());
    }

    @GetMapping("/healthyyy")
    public String healthCheck() {
        return "Still breathing!";
    }

}

