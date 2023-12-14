package com.anonymous.privacytool.service.impl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.Content;
import com.anonymous.privacytool.exception.EmailSendingException;
import com.anonymous.privacytool.service.EmailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private AmazonSimpleEmailService sesClient;

    @Value("${aws.ses.accessKey}")
    private String accessKey;

    @Value("${aws.ses.secretKey}")
    private String secretKey;

    @Value("${aws.ses.region}")
    private String region;

    @Value("${email.sender}")
    private String senderEmail;

    @PostConstruct
    private void initializeSES() {
        this.sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    @Override
    public void sendResetLink(String email, String resetUrl) throws EmailSendingException {
        String subject = "Password Reset Link";
        String bodyHtml = "<html><body><p>Please use the following link to reset your password:</p>"
                + "<a href='" + resetUrl + "'>" + "Reset password" + "</a></body></html>";

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content().withCharset("UTF-8").withData(bodyHtml)))
                        .withSubject(new Content().withCharset("UTF-8").withData(subject)))
                .withSource(senderEmail);

        sesClient.sendEmail(request);
    }
}
