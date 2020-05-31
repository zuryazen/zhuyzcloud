package com.zhuyz.cloud.controller;


import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.common.utils.UUIDUtils;
import com.zhuyz.cloud.entity.Payment;
import com.zhuyz.cloud.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.zhuyz.cloud.common.constant.Constant.StatusCode.QUERY_ERROR;
import static com.zhuyz.cloud.common.constant.Constant.StatusCode.SAVE_ERROR;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author zhuyz
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @GetMapping("/get/{id}")
    public ResponseEntity getPaymentById(@PathVariable("id") String id) {
        Payment payment = paymentService.getById(id);
        return payment != null ? ResponseEntity.buildSuccess(payment) : ResponseEntity.buildCustom(null, QUERY_ERROR, "查询失败或者存在");
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Payment payment) {
        payment.setId(UUIDUtils.create32Id());
        boolean save = paymentService.save(payment);
        return save ? ResponseEntity.buildSuccess() : ResponseEntity.buildCustom(null, SAVE_ERROR, "保存失败");
    }

}
