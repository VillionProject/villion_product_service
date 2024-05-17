package com.example.villion_product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VillionProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VillionProductServiceApplication.class, args);
    }

}
