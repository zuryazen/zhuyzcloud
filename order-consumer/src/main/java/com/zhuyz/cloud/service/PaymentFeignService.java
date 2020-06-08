package com.zhuyz.cloud.service;

import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(value = "PAYMENT-PROVIDER", fallback = PaymentFallbackService.class)
public interface PaymentFeignService {


    @GetMapping("/payment/get/{id}")
    ResponseEntity getPaymentById(@PathVariable("id") String id);

    @GetMapping("/payment/timeout")
    String paymentFeignTimeout();


    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") String id);


    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_timeout(@PathVariable("id") String id);

}
