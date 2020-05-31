package com.zhuyz.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhuyz
 * @date 2020/5/31 0031 21:13
 * @description
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class EurekaMain {


    public static void main(String[] args) {
        SpringApplication.run(EurekaMain.class, args);
    }
}
