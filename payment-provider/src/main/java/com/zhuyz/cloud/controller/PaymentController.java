package com.zhuyz.cloud.controller;


import com.zhuyz.cloud.common.utils.ResponseEntity;
import com.zhuyz.cloud.common.utils.UUIDUtils;
import com.zhuyz.cloud.entity.Payment;
import com.zhuyz.cloud.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private DiscoveryClient discoveryClient;



    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public ResponseEntity getPaymentById(@PathVariable("id") String id) {
        System.out.println("serverPort" + serverPort);
        Payment payment = paymentService.getById(id);
        return payment != null ? ResponseEntity.buildSuccess(payment) : ResponseEntity.buildCustom(null, QUERY_ERROR, "查询失败或者存在");
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Payment payment) {
        payment.setId(UUIDUtils.create32Id());
        boolean save = paymentService.save(payment);
        return save ? ResponseEntity.buildSuccess() : ResponseEntity.buildCustom(null, SAVE_ERROR, "保存失败");
    }

    @GetMapping("/discovery")
    public ResponseEntity gets() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("service: " +service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-PROVIDER");
        return ResponseEntity.buildSuccess(this.discoveryClient);
    }

    @GetMapping("/get/port")
    public String getPort() {
        return serverPort;
    }

    @GetMapping("/timeout")
    public String paymentFeignTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return serverPort;
    }

}
