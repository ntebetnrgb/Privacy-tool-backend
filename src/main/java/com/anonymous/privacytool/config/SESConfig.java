package com.anonymous.privacytool.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SESConfig {
    @Value("${aws.role.sts.ses}")
    private String stsRoleArn;
    public AmazonSimpleEmailService sesClient() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Constants.AWS_REGION)
                .withCredentials(new AWSStaticCredentialsProvider(assumeRole(stsRoleArn, Constants.SESSTSRoleSessionName)))
                .build();
    }

    private BasicSessionCredentials assumeRole(String roleArn, String roleSessionName) {
        AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest()
                .withRoleArn(roleArn)
                .withDurationSeconds(3600)
                .withRoleSessionName(roleSessionName);
        AssumeRoleResult assumeRoleResult = AWSSecurityTokenServiceClientBuilder.standard()
                .build()
                .assumeRole(assumeRoleRequest);

        return new BasicSessionCredentials(
                assumeRoleResult.getCredentials().getAccessKeyId(),
                assumeRoleResult.getCredentials().getSecretAccessKey(),
                assumeRoleResult.getCredentials().getSessionToken());
    }
}
