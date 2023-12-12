package com.anonymous.privacytool.controller;

import com.anonymous.privacytool.dto.ForgotPasswordRequest;
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
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        userService.processForgotPassword(request.getEmail());
        return ResponseEntity.ok("Password reset email sent.");
    }
}

