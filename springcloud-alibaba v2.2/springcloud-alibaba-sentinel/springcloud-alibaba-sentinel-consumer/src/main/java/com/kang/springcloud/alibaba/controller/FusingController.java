package com.kang.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FusingController {

    static int NUM_FUSING = 0;

    // http://127.0.0.1:8711/fusing
    @RequestMapping("/fusing")
    public String fusing() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        NUM_FUSING++;
        System.out.println("[熔断]>>>>"+NUM_FUSING);
        return "【熔断】....." + NUM_FUSING;
    }

    //熔断--异常比例
    @RequestMapping("/FusingErr")
    public String FusingErr() throws InterruptedException {
        System.out.println("[熔断--异常比例]>>>>");
        int i = 1 / 0;
        return "【熔断-异常比例】....." ;
    }

}
