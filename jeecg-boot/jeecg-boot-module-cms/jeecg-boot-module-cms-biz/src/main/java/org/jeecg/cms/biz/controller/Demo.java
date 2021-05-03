package org.jeecg.cms.biz.controller;



import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.jeecg.cms.biz.service.impl.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("/cms")
public class Demo {

@Autowired
private DemoServiceImpl demoService;
    @RequestMapping("/demo")
    public  String Demo(){

//        demoService.demo();
        return "hello";
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("my_vhost");
        factory.setHost("192.168.101.14");
        factory.setPort(5672);

        Connection conn = factory.newConnection();
        System.out.println(conn);

    }


}
