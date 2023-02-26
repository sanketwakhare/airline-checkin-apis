package com.sanket.airlinecheckin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AirlineCheckinApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirlineCheckinApisApplication.class, args);
    }

}
