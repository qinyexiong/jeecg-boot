package org.jeecg.cms.biz.controller;



import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.jeecg.cms.conf.RabbitMqOperator;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("/cms")
public class Demo {

    //消息队列
    private AmqpTemplate amqpTemplate;

    @Autowired
   private  RabbitMqOperator rabbitMqOperator;
    @Autowired
    public Demo(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping("/demo")
    public  String Demo(){
        byte[] data = new byte[11];
        // data[10] = ;
        //发送数据到first_queue队列
        rabbitMqOperator.push("first_queue" ,data);

        for (int i = 0; i < 100; i++){
            //发送数据到second_queue队列
            rabbitMqOperator.pushInt("second_queue",i);
        }

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
