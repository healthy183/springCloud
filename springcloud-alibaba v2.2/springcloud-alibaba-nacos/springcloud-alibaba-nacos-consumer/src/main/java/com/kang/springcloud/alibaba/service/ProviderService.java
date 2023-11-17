package com.kang.springcloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(contextId ="springcloud-alibaba-nacos-provider-providerService",
        name = "springcloud-alibaba-nacos-provider"
        //,path = ""
)
public interface ProviderService {
        //声明需要调用的rest接口对应的方法名字、接口格式
        @RequestMapping("/getProductMsg")
        String getProductMsg();
}
