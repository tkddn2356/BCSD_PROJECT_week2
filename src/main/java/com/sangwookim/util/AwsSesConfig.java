package com.sangwookim.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClient;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j
public class AwsSesConfig {

    @Value("${AWS_ACCESS_KEY_ID}")
    private String AWS_ACCESS_KEY_ID;


    @Value("${AWS_SECRET_KEY}")
    private String AWS_SECRET_KEY;

    @Bean
    public AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync() {
        log.info(AWS_ACCESS_KEY_ID);
        log.info(AWS_SECRET_KEY);
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);

        return AmazonSimpleEmailServiceAsyncClient.asyncBuilder()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(Regions.US_WEST_2)
                .build();
    }
}
