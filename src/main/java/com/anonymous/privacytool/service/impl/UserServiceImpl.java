package com.anonymous.privacytool.service.impl;
import com.anonymous.privacytool.entity.AuthUser;
import com.anonymous.privacytool.exception.EmailSendingException;
import com.anonymous.privacytool.repository.AuthUserRepository;
import com.anonymous.privacytool.service.EmailService;
import com.anonymous.privacytool.exception.UserNotFoundException;
import com.anonymous.privacytool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthUserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void processForgotPassword(String email) {
        AuthUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String token = generateResetToken();
        user.setResetToken(token);
        userRepository.save(user);

        String resetUrl = "https://api.security.harshalkhodifad.com/user/reset-password?token=" + token;
        try {
            emailService.sendResetLink(email, resetUrl);
        } catch (Exception e) {
            throw new EmailSendingException("Failed to send reset email");
        }
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}
