package com.drew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DrewStaticService {

    public static void main(String[] args){
        SpringApplication.run(DrewStaticService.class,args);
    }
}
