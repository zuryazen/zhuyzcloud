package com.zhuyz.cloud.controller;

import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhuyz
 * @date 2020/5/30 0030 18:34
 * @description
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public ResponseEntity create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ResponseEntity.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public ResponseEntity get(@PathVariable("id") String id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ResponseEntity.class);
    }



}
