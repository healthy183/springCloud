package com.kang.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HotdistController {

    // http://127.0.0.1:8711/hotDist/123
    //热点规则  必须配合SentinelResource 使用
    @RequestMapping("/hotDist/{id}")
    @SentinelResource(value = "hotDist", blockHandler = "hotDistBlockHandler")
    public String hotDist(@PathVariable("id") Integer id) throws InterruptedException {
        System.out.println("[热点规则]>>>>:" + id);
        return "【热点规则】.....";
    }

    //SentinelResource 必须 方法名字，参数一样
    public String hotDistBlockHandler(@PathVariable("id") Integer id,BlockException blockException) throws InterruptedException {
        System.out.println("[热点规则----异常处理 id=" + id);
        return "【热点规则---异常处理】.....";
    }



}
