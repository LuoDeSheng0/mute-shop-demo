package com.mute;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: mute-shop-demo
 * @description:
 * @author: mute_luo
 * @create: 2019-09-20 10:42
 */
@SpringBootApplication
@MapperScan("com.mute.dao")
@EnableEurekaClient
public class MemberApplication {
    public static void main(String[] args){
        SpringApplication.run(MemberApplication.class,args);
    }
}
