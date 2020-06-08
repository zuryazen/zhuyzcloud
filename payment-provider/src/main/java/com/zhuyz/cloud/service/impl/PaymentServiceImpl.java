package com.zhuyz.cloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.zhuyz.cloud.entity.Payment;
import com.zhuyz.cloud.mapper.PaymentMapper;
import com.zhuyz.cloud.service.IPaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

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

    /**
     * 正常访问，OK
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(String id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK, id: " + id + "\t" + "O(∩_∩)O";
    }

    /**
     * 超时访问，error
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @Override
    public String paymentInfo_timeout(String id) {
//      int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_timeout, id: " + id + "\t" + "O(∩_∩)O";
    }

    public String paymentInfo_timeoutHandler(String id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_timeoutHandler系统繁忙，请稍候再试, id: " + id + "\t" + "/(ㄒoㄒ)/~~";
    }

    /*服务熔断*/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数超过了峰值，熔断器从关闭变为打开
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")    // 失败率达到多少后跳闸
    })
    @Override
    public String paymentCircuitBreaker(String id){
        if ("error".equals(id)) {
             throw new RuntimeException("error~~~~~");
        }
        String s = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号："  + s;
    }

    public String paymentCircuitBreaker_fallback(String id) {
        return "id :" + id + " 不能为error，请稍后再试";
    }
}
