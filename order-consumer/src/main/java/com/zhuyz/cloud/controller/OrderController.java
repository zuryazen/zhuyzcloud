package com.zhuyz.cloud.controller;

import com.netflix.discovery.converters.Auto;
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


}
