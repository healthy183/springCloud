package com.kang.springcloud.alibaba.feign2.callback;

import com.kang.springcloud.alibaba.feign2.OrderFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @desc:  Hystrix断路器
 * @author: cao_wencao
 * @date: 2020-04-29 20:45
 */
@Component
@Slf4j
public class OrderFeignFallbackFactoryImpl implements FallbackFactory<OrderFeign> {
    @Override
    public OrderFeign create(Throwable throwable) {
        log.error(throwable.toString());
        return new OrderFeign() {
            @Override
            public String getProductInfo(String string) {
                return "fallback; reason was: "+ throwable;
            }
        };
    }
}
