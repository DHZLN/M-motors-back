package com.m_motors.mmotors;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {"com.m_motors.mmotors"})
public class MMotorsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MMotorsAppApplication.class, args);
    }
}