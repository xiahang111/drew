package com.drew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.drew.mapper")
public class DrewBootManage {

    public static void main(String[] args){
        SpringApplication.run(DrewBootManage.class,args);
    }

}
