package com.zhuyz.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhuyz
 * @date 2020/5/30 0030 18:33
 * @description
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class OrderMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain.class, args);
    }

}
