package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(proxyBeanMethods = false, exclude = ValidationAutoConfiguration.class)
@EnableRetry
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
