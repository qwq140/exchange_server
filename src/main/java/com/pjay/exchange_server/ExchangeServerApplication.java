package com.pjay.exchange_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ExchangeServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeServerApplication.class, args);
    }

}
