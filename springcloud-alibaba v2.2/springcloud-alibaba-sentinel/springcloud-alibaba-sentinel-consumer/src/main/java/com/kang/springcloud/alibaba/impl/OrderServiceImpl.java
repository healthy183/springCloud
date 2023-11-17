package com.kang.springcloud.alibaba.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kang.springcloud.alibaba.api.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    // 定义为Sentinel 的资源  可以对方法流控！
    @SentinelResource(value = "orderInfo", blockHandler = "OrderInfoBlockHandler")
    public String orderInfo() {
        return "【订单测试】---####";
    }

    //SentinelResource 异常处理
    public String OrderInfoBlockHandler(BlockException e) {
        return "o(╥﹏╥)o....系统流量过大(链路限流)......加班维护中......." + e;
    }
}
