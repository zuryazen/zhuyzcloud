package com.zhuyz.myrules;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuyz
 * @date 2020/6/1 0001 21:26
 * @description
 */
@Configuration
public class MyRules {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
