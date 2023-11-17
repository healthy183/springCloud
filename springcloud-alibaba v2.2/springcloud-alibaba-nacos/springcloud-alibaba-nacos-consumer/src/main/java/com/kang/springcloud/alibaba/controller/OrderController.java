package com.kang.springcloud.alibaba.controller;

import com.kang.springcloud.alibaba.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @desc:  消费者——>订单服务
 * @author: cao_wencao
 * @date: 2020-04-17 20:58
 */
@Slf4j
@RestController
public class OrderController {

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    //方式一 ：通过IP地址 http://127.0.0.1:8710/getProductMsg
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        String url = "http://127.0.0.1:8715/getProductMsg";
        String response = restTemplate.getForObject(url, String.class);
        log.info("restTemplate IP访问：response==>: {}",response);
        return response;
    }

    //方式二 ：通过服务名（这种方式不可行，在Eureka中可以）
    @Deprecated
    @GetMapping("/getProductMsg2")
    public String getProductMsg2() {
        String url = "http://springcloud-alibaba-nacos-provider/getProductMsg";
        String response = restTemplate.getForObject(url, String.class);
        log.info("response==>: {}",response);
        return response;
    }

    //方式三 ：通过LoadBalancerClient获取服务调用地址进行调用（默认通过ribbon实现负载均衡）
    @GetMapping("/getProductMsg3")
    public String getProductMsg3() {
        //discoveryClient.getInstances()
        ServiceInstance serviceInstance = loadBalancerClient.choose("springcloud-alibaba-nacos-provider");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/getProductMsg");
        String response = restTemplate.getForObject(url, String.class);
        log.info("loadBalancerClient IP访问： response结果==>>>: {}", response);
        return response;
    }

    //方式四 ：通过discoveryClient获取服务调用地址进行调用
    @GetMapping("/getProductMsg4")
    public String getProductMsg4() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("springcloud-alibaba-nacos-provider");
        //任意选择一个，实现本地RPC调用
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        String response = restTemplate.getForObject(serviceInstance.getUri()+ "/getProductMsg", String.class);
        log.info("discoveryClient response结果==>>>: {}", response);
        return response;
    }



    @Autowired
    private  ProviderService providerService;

    @GetMapping("/getProductMsg5")
    public String getProductMsg5() {
        String productMsg = providerService.getProductMsg();
        log.info("providerService response结果==>>>: {}", productMsg);
        return productMsg;
    }

}

