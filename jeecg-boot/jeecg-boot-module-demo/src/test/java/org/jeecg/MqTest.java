package org.jeecg;

import org.jeecg.boot.starter.rabbitmq.client.RabbitMqClient;
import org.jeecg.common.base.BaseMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = JeecgDemoCloudApplication.class)
public class MqTest {
    @Autowired
    private RabbitMqClient rabbitMqClient;

	@Test
	public void testt() {
		BaseMap map = new BaseMap();
		map.put("orderId", "12345");
		rabbitMqClient.sendMessage("test", map);
//延迟10秒发送
//rabbitMqClient.sendMessage("test", map,10000);
	}



}
