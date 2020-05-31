package com.zhuyz.cloud.service.impl;

import com.zhuyz.cloud.entity.Payment;
import com.zhuyz.cloud.mapper.PaymentMapper;
import com.zhuyz.cloud.service.IPaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author zhuyz
 * @since 2020-05-30
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

}
