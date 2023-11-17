package com.kang.springcloud.alibaba.feign2;

import com.kang.springcloud.alibaba.feign2.callback.OrderFeignFallbackFactoryImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @desc:   订单调用商品服务
 * @author: cao_wencao
 * @date: 2020-04-29 20:19
 */
@FeignClient(contextId = "OrderFeign" , name = "springcloud-alibaba-feign-provider",fallbackFactory = OrderFeignFallbackFactoryImpl.class)
public interface OrderFeign {

    @GetMapping(value = "/getProductInfo/{productId}")
    String getProductInfo(@PathVariable("productId") String productId);

    class HystrixClientFallback implements OrderFeign {
        @Override
        public String getProductInfo(String productId) {
            return "fallback; reason was:  服务忙，稍后重试" ;
        }
    }
}
