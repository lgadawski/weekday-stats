package com.gadawski.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(value = "com.gadawski")
@EnableScheduling
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class);
    }
}
