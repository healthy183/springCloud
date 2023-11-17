package com.kang.springcloud.alibaba.controller;


import com.kang.springcloud.alibaba.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/fruit")
public class FruitController {

    static int TEST1_ADD = 0;
    static int TEST2_ADD = 0;

    @Autowired
    private OrderService orderService;

    // http://127.0.0.1:8711/fruit/test1
    @RequestMapping("/test1")
    public String test1() {
        System.out.println("test1:"+TEST1_ADD++);
        return "test1【畅游["+TEST1_ADD+"]....::>>】" + orderService.orderInfo();
    }

    @RequestMapping("/test2")
    public String test2() {
        System.out.println("test2:"+TEST2_ADD++);
        return "test2【链路限流["+TEST2_ADD+"]....::>>】" + orderService.orderInfo();
    }

}
