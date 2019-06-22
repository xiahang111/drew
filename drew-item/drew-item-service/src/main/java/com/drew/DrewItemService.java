package com.drew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.drew.mapper")
public class DrewItemService {

    public static void main(String[] args){
        SpringApplication.run(DrewItemService.class,args);
    }
}
