package org.jeecg.cms.biz.service.impl;

import org.jeecg.boot.starter.rabbitmq.client.RabbitMqClient;
import org.jeecg.cms.biz.service.DemoService;
import org.jeecg.common.base.BaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DemoServiceImpl implements DemoService {

    @Autowired(required = false)
    private RabbitMqClient rabbitMqClient;

    @Override
    public void demo() {

        BaseMap map = new BaseMap();
        map.put("orderId", "12345");
        rabbitMqClient.sendMessage("test", map);
//延迟10秒发送
//rabbitMqClient.sendMessage("test", map,10000);
    }
}
