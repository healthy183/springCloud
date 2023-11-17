package com.kang.springcloud.alibaba.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 关于Feign Hystrix Fallbacks：
 * 第一种：Hystrix支持回退的概念：当它们的电路断开或出现错误时，将执行默认代码路径。要为给定@FeignClient集启用后备，
 * 该fallback属性应为实现后备的类名称。您还需要将实现声明为Spring bean。
 *
 * @desc:   订单调用商品服务
 * @author: cao_wencao
 * @date: 2020-04-29 20:19
 */
@FeignClient(contextId = "OrderFeignFallback" ,name = "springcloud-alibaba-feign-provider",fallback = OrderFeignFallback.OrderFeignFallbackImpl.class)
public interface OrderFeignFallback {

    @GetMapping(value = "/getProductInfo/{productId}")
    String getProductInfo(@PathVariable("productId") String productId);

    @Component
    class OrderFeignFallbackImpl implements OrderFeignFallback {
        @Override
        public String getProductInfo(String productId) {
            return "fallback; reason was:  服务忙，稍后重试" ;
        }
    }
}