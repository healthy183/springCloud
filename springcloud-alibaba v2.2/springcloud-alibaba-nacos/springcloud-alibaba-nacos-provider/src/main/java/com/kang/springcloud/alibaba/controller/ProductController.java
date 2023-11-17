package com.kang.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProductController {

    @Value("${server.port}")
    private String port;


    //方式一 ：通过IP地址 http://127.0.0.1:8222/getProductMsg
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        log.info("开始调用商品服务信息啦");
        return "我是商品生产者服务, 调用商品服务接口成功了===>> : " + port;
    }
}