package com.anonymous.privacytool.service;

public interface EmailService {
    void sendResetLink(String email, String resetUrl);
}
