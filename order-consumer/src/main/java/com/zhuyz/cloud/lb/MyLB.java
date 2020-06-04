package com.zhuyz.cloud.lb;

import io.swagger.models.auth.In;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhuyz
 * @date 2020/6/4 0004 18:19
 * @description
 */
@Configuration
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getAndIncrement() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = getAndIncrement() % instances.size();
        return instances.get(index);
    }
}
