package com.zhuyz.cloud.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.entity.Payment;
import com.zhuyz.cloud.lb.LoadBalancer;
import com.zhuyz.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuyz
 * @date 2020/5/30 0030 18:34
 * @description
 */
@RestController
@RequestMapping("/order")
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderController {

    public static final String PAYMENT_URL = "http://PAYMENT-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/create")
    public ResponseEntity create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ResponseEntity.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ResponseEntity.class);
    }

    @GetMapping("/consumer/payment/get/feign/{id}")
    public ResponseEntity getForFeign(@PathVariable("id") String id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/get/lb")
    public String getLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-PROVIDER");
        ServiceInstance instance = loadBalancer.instance(instances);
        return restTemplate.getForObject(instance.getUri() + "/payment/get/port", String.class);
    }

    @GetMapping("/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") String id) {
        return paymentFeignService.paymentInfo_OK(id);
    }



    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") String id) {
        int age = 10/0;
        return paymentFeignService.paymentInfo_timeout(id);
    }

    public String paymentInfo_timeoutHandler(String id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_timeoutHandler系统繁忙，请稍候再试, id: " + id + "\t" + "/(ㄒoㄒ)/~~";
    }

    public String payment_Global_FallbackMethod() {
        return "Global 全局异常处理，请稍后再试";
    }

}
