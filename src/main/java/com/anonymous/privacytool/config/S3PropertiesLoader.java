package com.anonymous.privacytool.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.IOException;
import java.util.Properties;

public class S3PropertiesLoader implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String S3_BUCKET = "privacy-tool";
    private static final String S3_KEY = "local.properties";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Constants.AWS_REGION)
                .build();

        try {
            try (S3ObjectInputStream s3is = s3Client.getObject(S3_BUCKET, S3_KEY).getObjectContent()) {
                Properties properties = new Properties();
                properties.load(s3is);
                applicationContext.getEnvironment().getPropertySources().addLast(
                        new PropertiesPropertySource("s3Properties", properties));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file from S3", e);
        }
    }
}
