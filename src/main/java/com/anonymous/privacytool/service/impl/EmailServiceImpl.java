package com.anonymous.privacytool.service.impl;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.Content;
import com.anonymous.privacytool.config.SESConfig;
import com.anonymous.privacytool.exception.EmailSendingException;
import com.anonymous.privacytool.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    SESConfig sesConfig;

    @Value("${email.sender}")
    private String senderEmail;

    @Override
    public void sendResetLink(String email, String resetUrl) throws EmailSendingException {
        String subject = "Password Reset Link";
        String bodyHtml = "<html><body><p>Please use the following link to reset your password:</p>"
                + "<a href='" + resetUrl + "'>" + "Reset password" + "</a></body></html>";
        String charset = "UTF-8";

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content().withCharset(charset).withData(bodyHtml)))
                        .withSubject(new Content().withCharset(charset).withData(subject)))
                .withSource(senderEmail);

        sesConfig.sesClient().sendEmail(request);
    }
}
