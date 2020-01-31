package com.kkb.temperature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.kkb"})
public class TemperatureApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemperatureApplication.class, args);
    }
}
