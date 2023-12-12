package com.anonymous.privacytool.service.impl;

import com.anonymous.privacytool.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    // AWS SES client setup and configuration

    @Override
    public void sendResetLink(String email, String resetUrl) {
        // Implementation to send email using AWS SES
        // Handle exceptions and errors appropriately
    }

    // Implement other methods declared in the EmailService interface
}