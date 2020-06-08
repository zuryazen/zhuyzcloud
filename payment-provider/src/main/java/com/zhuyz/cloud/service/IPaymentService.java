package com.zhuyz.cloud.service;

import com.zhuyz.cloud.entity.Payment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author zhuyz
 * @since 2020-05-30
 */
public interface IPaymentService extends IService<Payment> {

    String paymentInfo_OK(String id);

    String paymentInfo_timeout(String id);

    String paymentCircuitBreaker(String id);

}
