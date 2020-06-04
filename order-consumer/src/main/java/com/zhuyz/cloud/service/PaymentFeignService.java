package com.zhuyz.cloud.service;

import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

import static com.zhuyz.cloud.common.constant.Constant.StatusCode.QUERY_ERROR;

@Component
@FeignClient(value = "PAYMENT-PROVIDER")
public interface PaymentFeignService {


    @GetMapping("/payment/get/{id}")
    ResponseEntity getPaymentById(@PathVariable("id") String id);

    @GetMapping("payment/timeout")
    String paymentFeignTimeout();

}
