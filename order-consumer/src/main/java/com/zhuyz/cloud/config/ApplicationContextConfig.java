package com.zhuyz.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhuyz
 * @date 2020/5/30 0030 18:39
 * @description
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
