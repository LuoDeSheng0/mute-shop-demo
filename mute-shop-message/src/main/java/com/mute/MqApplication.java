package com.mute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-25 10:23
 */
@SpringBootApplication
@EnableEurekaClient
public class MqApplication {
    public static void main(String[] args){
        SpringApplication.run(MqApplication.class,args);
    }
}
