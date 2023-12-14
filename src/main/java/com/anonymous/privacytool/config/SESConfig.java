package com.anonymous.privacytool.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SESConfig {
    @Bean
    public AmazonSimpleEmailService sesClient() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(Constants.AWS_REGION)
                .build();
    }
}
