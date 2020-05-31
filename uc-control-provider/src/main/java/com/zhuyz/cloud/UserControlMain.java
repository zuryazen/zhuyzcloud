package com.zhuyz.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhuyz
 * @date 2020/5/30 0030 13:03
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.zhuyz.cloud.mapper")
public class UserControlMain {

    public static void main(String[] args) {
        SpringApplication.run(UserControlMain.class, args);
    }

}
