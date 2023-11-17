package com.kang.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc:   生产者-商品服务
 * @author: cao_wencao
 * @date: 2020-04-29 19:48
 */
@RestController
@Slf4j
public class ProviderController {

    //http://127.0.0.1:8013/getProductInfo/1010110
    @GetMapping(value = "/getProductInfo/{productId}")
    public String getProductInfo(@PathVariable("productId") String productId) {
        log.info("请求进来啦");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello Nacos Discovery " + productId;
    }
}