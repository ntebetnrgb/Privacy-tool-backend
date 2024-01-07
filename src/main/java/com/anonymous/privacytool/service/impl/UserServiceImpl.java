package com.anonymous.privacytool.service.impl;

import com.anonymous.privacytool.config.JwtGenerator;
import com.anonymous.privacytool.dto.GenericResponse;
import com.anonymous.privacytool.entity.User;
import com.anonymous.privacytool.exception.EmailSendingException;
import com.anonymous.privacytool.repository.AuthUserRepository;
import com.anonymous.privacytool.service.EmailService;
import com.anonymous.privacytool.exception.UserNotFoundException;
import com.anonymous.privacytool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthUserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Value("${application.endpoint}")
    private String applicationEndpoint;

    @Autowired
    JwtGenerator jwtGenerator;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    private AuthUserRepository user;

    @Override
    public void processForgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String token = generateResetToken();
        user.setResetToken(token);
        user.setUpdatedBy(user.getEmail());
        userRepository.save(user);

        String resetUrl = applicationEndpoint + "/user/reset-password?token=" + token;
        try {
            emailService.sendResetLink(email, resetUrl);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Setup logging
            throw new EmailSendingException("Failed to send reset email");
        }
    }

    @Override
    public ResponseEntity<GenericResponse> signUpUser(User user) throws Exception {
        boolean alreadyRegister=checkIfUserIsAlreadyRegistered(user);

        if(alreadyRegister==true){
            throw new Exception("User already registered");
        }

        userRepository.save(user);
        return ResponseEntity.ok().body(GenericResponse.builder().message("Sign Up successfull...!!").build());
    }

    private boolean checkIfUserIsAlreadyRegistered(User user) {
        Optional<User> findUser=userRepository.findByEmail(user.getEmail());
        if(findUser.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> getUser(String email) {
        Optional<User> user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public ResponseEntity<GenericResponse> verifyUserLogin(User user) throws Exception {
         Optional<User> findUser=userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if(!findUser.isPresent()){
            return ResponseEntity.ok().body(GenericResponse.builder().message("pls signup or password is incorrect").build());
        }

        String token = jwtGenerator.generateToken(user.getEmail());
        return ResponseEntity.ok().body(GenericResponse.builder().message("Logged in....!!!!").data(token).build());
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}
