package com.kang.nacos.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
public class ShareController {

    @Value("${shared.zero}")
    private String shareZero;

    @Value("${shared.one}")
    private String shareOne;

    //@Value("${share.config2}")
    private String shareConfig2;


    //http://127.0.0.1:8010/getShareZero
    @RequestMapping("/getShareZero")
    public String getShareZero() {
        return shareZero;
    }

    //http://127.0.0.1:8010/getShareOne
    @RequestMapping("/getShareOne")
    public String getShareOne() {
        return shareOne;
    }

    //http://127.0.0.1:8010/getShare2
    @RequestMapping("/getShare2")
    public String getShare2() {
        return shareConfig2;
    }

}
