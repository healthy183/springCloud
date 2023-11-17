package com.kang.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @desc:  消费者——>订单服务
 * @author: cao_wencao
 * @date: 2020-04-17 20:58
 */
@Slf4j
@RestController
public class OrderController {

    static int NUN_ADD = 0;
    static int NUN_FIND = 0;
    static int NUN_THEAD = 0;


    // http://127.0.0.1:8711/add
    @RequestMapping("/add")
    @SentinelResource(value = "add", blockHandler = "addBlockHandler")
    public String add() {
        NUN_ADD++;
        System.out.println("下单["+ NUN_ADD +"]+成功");
        return  "Hellow Spring-Cloud(Sentinel_NUN_ADD请求)------["+ NUN_ADD + "]次---";
    }

    //自定义流量。
    //addBlockHandler 与 add 参数类型都得一样
    public String addBlockHandler(BlockException e) {
        return "o(╥﹏╥)o....系统流量过大......加班维护中......." + e;
    }

    // http://127.0.0.1:8711/find
    @RequestMapping("/find")
    public String find() {
        NUN_FIND++;
        System.out.println("查找["+ NUN_FIND +"]成功);");
        return "Hellow Spring-Cloud(Sentinel_NUN_FIND请求)------["+ NUN_FIND +" 次";
    }

    @RequestMapping("/findThead")
    public String findThead() throws InterruptedException {
        NUN_THEAD++;
        System.out.println("查找【" + NUN_THEAD + "】+成功");
        //休眠5秒
        TimeUnit.SECONDS.sleep(5);
        return "Hellow Spring-Cloud(Sentinel_NUN_THEAD请求)------[" + NUN_THEAD + " ]次---";
    }

}

