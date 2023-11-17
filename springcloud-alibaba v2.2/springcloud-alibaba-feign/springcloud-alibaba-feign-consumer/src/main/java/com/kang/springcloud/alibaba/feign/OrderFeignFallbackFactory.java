package com.kang.springcloud.alibaba.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 如果需要访问引起后备触发器的原因，则可以使用fallbackFactory inside的属性@FeignClient。
 * 说白了就是如果你想知道服务调用为什么失败了，可以使用下面这种断路器方式。
 *
 * @desc:   订单调用商品服务
 * @author: cao_wencao
 * @date: 2020-04-29 20:19
 */
@FeignClient(contextId = "OrderFeignFallbackFactory",name = "springcloud-alibaba-feign-provider",fallbackFactory = OrderFeignFallbackFactory.OrderFeignFallbackFactoryImpl.class)
public interface OrderFeignFallbackFactory {

    @GetMapping(value = "/getProductInfo/{productId}")
    String getProductInfo(@PathVariable("productId") String productId);

    @Component
    @Slf4j
    class OrderFeignFallbackFactoryImpl implements FallbackFactory<OrderFeignFallbackFactory> {
        @Override
        public OrderFeignFallbackFactory create(Throwable throwable) {
            log.error("调用异常："+ throwable.toString());
            return new com.kang.springcloud.alibaba.feign.OrderFeignFallbackFactory() {
                @Override
                public String getProductInfo(String string) {
                    return "开启断路-fallback; reason was: "+ throwable;
                }
            };
        }
    }
}
