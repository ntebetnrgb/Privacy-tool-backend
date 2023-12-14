package com.anonymous.privacytool.service;

import com.anonymous.privacytool.exception.EmailSendingException;

public interface EmailService {
    void sendResetLink(String email, String resetUrl) throws EmailSendingException;
}
