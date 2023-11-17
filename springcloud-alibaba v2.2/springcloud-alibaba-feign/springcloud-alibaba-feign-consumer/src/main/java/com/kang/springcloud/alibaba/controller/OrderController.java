package com.kang.springcloud.alibaba.controller;

import com.kang.springcloud.alibaba.feign.OrderFeignFallback;
import com.kang.springcloud.alibaba.feign.OrderFeignFallbackFactory;
import com.kang.springcloud.alibaba.feign2.OrderFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @Autowired(required = false)
    private OrderFeignFallback orderFeignFallback;

    @Autowired(required = false)
    private OrderFeignFallbackFactory orderFeignFallbackFactory;

    //http://127.0.0.1:8012/getProductFallback/1010110
    @GetMapping("/getProductFallback/{productId}")
    public String getProductFallback(@PathVariable("productId") String id){
       String response = "response";
       response = orderFeignFallback.getProductInfo(id);
        log.info("调用服务结束： "+ response);
        return response;
    }

    //http://127.0.0.1:8012/getProductFactory/1010110
    @GetMapping("/getProductFactory/{productId}")
    public String getProductFactory(@PathVariable("productId") String id){
        String response = orderFeignFallbackFactory.getProductInfo(id);
        log.info("调用服务结束： "+ response);
        return response;
    }

    @Autowired(required = false)
    private OrderFeign orderFeign;

    //http://127.0.0.1:8012/getProduct/1010110
    @GetMapping("/getProduct/{productId}")
    public String getProduct(@PathVariable("productId") String id){
        String response = "String response ";
        response = orderFeign.getProductInfo(id);
        log.info("调用服务结束： "+ response);
        return response;
    }
}