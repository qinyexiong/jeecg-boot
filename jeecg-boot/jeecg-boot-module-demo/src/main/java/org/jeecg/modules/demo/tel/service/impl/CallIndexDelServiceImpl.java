package org.jeecg.modules.demo.tel.service.impl;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.sun.istack.Nullable;
import org.apache.commons.lang.StringUtils;

import org.jeecg.boot.starter.rabbitmq.client.RabbitMqClient;
import org.jeecg.common.base.BaseMap;
import org.jeecg.modules.demo.tel.entity.CallIndexDel;
import org.jeecg.modules.demo.tel.entity.Converter;
import org.jeecg.modules.demo.tel.mapper.CallIndexDelMapper;
import org.jeecg.modules.demo.tel.service.ICallIndexDelService;
import org.springframework.amqp.core.AbstractExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.client.RestTemplate;


import java.io.*;

import java.util.*;

/**
 * @Description: index_del页面
 * @Author: jeecg-boot
 * @Date: 2021-03-29
 * @Version: V1.0
 */
@Service
public class CallIndexDelServiceImpl extends ServiceImpl<CallIndexDelMapper, CallIndexDel> implements ICallIndexDelService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitMqClient rabbitMqClient;




    @Override
    public int AddMq() {
//        BaseMap params = new BaseMap();
//        params.put("orderId", "123456");
//        rabbitMqClient.publishEvent("demoBusEvent", params);


        rabbitMqClient.createExchange("hangman");
        Queue que1 = new Queue("que1");
        rabbitMqClient.addQueue(que1);
        AbstractExchange abstractExchange = new AbstractExchange("asd") {
            @Override
            public String getType() {
                return "topic";
            }
        };
        rabbitMqClient.addExchange(abstractExchange);
//        rabbitMqClient.addBinding(que1,"zhangsan","a","1");
        return 0;
    }

    @Override
    public int pull(CallIndexDel t) {
        int num = 0;
        if (StringUtils.isEmpty(t.getToken())) {
            throw new RuntimeException("StringUtils.isEmpty(t.getToken())");
        }
        try {
            List<CallIndexDel> indexData = getIndexData(t);
            this.saveBatch(indexData, 1000);
            num = indexData.size();
            log.debug("一共提取" + indexData.size() + "条记录");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }



    public List<CallIndexDel> getIndexData(CallIndexDel t) throws IOException {
        long total = 0;
        long total_loca = 0;
        long current = 0;
        long size = 10;
        String url = "https://bifrost.tianjiinfo.com/distribute/mng/follow/client/page";//指定URL
        Map<String, Object> params = new HashMap<>();
        params.put("tsToken", t.getToken());
        List<CallIndexDel> callIndexDels = new ArrayList<>();
        while (total_loca <= total) {
            params.put("current", current);
            params.put("size", size);
            ResponseEntity<String> re = restTemplate.postForEntity(url, params, String.class);
            String body = re.getBody();
            Converter.Redata4Rque redata4Rque = Converter.fromJsonString(body);
            total = redata4Rque.getResult().getTotal();
            if (Objects.isNull(redata4Rque)) {
                throw new RuntimeException("Objects.isNull(redata4Rque)");
            }
            if (!redata4Rque.getCode().equals("200")) {
                throw new RuntimeException("!redata4Rque.getCode().equals(\"200\")");
            }
            Converter.Redata4Rque.Record[] records = redata4Rque.getResult().getRecords();
            for (Converter.Redata4Rque.Record r : records) {
                CallIndexDel callIndexDel = new CallIndexDel();
                callIndexDel.setClientid(r.getFollow().getClientID());
                callIndexDel.setUserId(r.getFollow().getUserID());
                callIndexDel.setRealname(r.getClient().getRealName());
                callIndexDel.setGender(r.getClient().getGender());
                callIndexDel.setIdcard(r.getClient().getIDCard());
                callIndexDel.setOrimobile(r.getClient().getOriMobile());
                callIndexDel.setMobile(r.getClient().getMobile());
                callIndexDels.add(callIndexDel);
                total_loca = total_loca + 1;
            }
            current = current + 1;
            log.debug("已经提取" + total_loca + "条信息");
        }

        return callIndexDels;
    }
}






