package com.zhuyz.cloud.service.impl;

import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.service.PaymentFeignService;
import org.springframework.stereotype.Component;

/**
 * @author zhuyz
 * @date 2020/6/8 0008 20:41
 * @description
 */
@Component
public class PaymentFallbackService implements PaymentFeignService {

    @Override
    public ResponseEntity getPaymentById(String id) {
        return ResponseEntity.buildCustom("---PaymentFallbackService fall back-getPaymentById", -1);
    }

    @Override
    public String paymentFeignTimeout() {
        return "---PaymentFallbackService fall back-paymentFeignTimeoutK";
    }

    @Override
    public String paymentInfo_OK(String id) {
        return "---PaymentFallbackService fall back-paymentInfo_OK";
    }

    @Override
    public String paymentInfo_timeout(String id) {
        return "---PaymentFallbackService fall back-paymentInfo_timeout";
    }
}
