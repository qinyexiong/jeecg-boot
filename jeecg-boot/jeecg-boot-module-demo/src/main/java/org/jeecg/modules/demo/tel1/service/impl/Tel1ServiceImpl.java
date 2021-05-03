package org.jeecg.modules.demo.tel1.service.impl;

import java.util.ArrayList;
import java.util.Date;

import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.tel1.entity.Tel1;
import org.jeecg.modules.demo.tel1.mapper.Tel1Mapper;
import org.jeecg.modules.demo.tel1.service.ITel1Service;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 测试表
 * @Author: jeecg-boot
 * @Date: 2021-03-26
 * @Version: V1.0
 */
@Service
@Slf4j
public class Tel1ServiceImpl extends ServiceImpl<Tel1Mapper, Tel1> implements ITel1Service {
    @Resource(name = "defaultThreadPool")
    private ThreadPoolTaskExecutor poolTaskExecutor;


    @Override
    public void save1(Tel1 tel1) {
        long t1 = System.currentTimeMillis();
        //使用Future方式执行多任务
        //生成一个集合
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<?> future = poolTaskExecutor.submit(() -> {
                LinkedList<Tel1> tel1s = productData(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + "=====办理业务");
                this.saveBatch(tel1s, 100);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "=====完成业务办理");
            });
            futures.add(future);
        }
        //查询任务执行的结果
        for (Future<?> f : futures) {
            while (true) {//CPU高速轮询：每个future都并发轮循，判断完成状态然后获取结果，这一行，是本实现方案的精髓所在。即有10个future在高速轮询，完成一个future的获取结果，就关闭一个轮询
                if (f.isDone() && !f.isCancelled()) {//获取future成功完成状态，如果想要限制每个任务的超时时间，取消本行的状态判断+future.get(1000*1, TimeUnit.MILLISECONDS)+catch超时异常使用即可。
                    log.info("SUCCESS");
                    break;//当前future获取结果完毕，跳出while
                } else {
                    try {
                        Thread.sleep(1);//每次轮询休息1毫秒（CPU纳秒级），避免CPU高速轮循耗空CPU
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("总共花费时间为：" + (t2 - t1));


    }


    //构建数据
    private LinkedList<Tel1> productData(String name) {
        LinkedList<Tel1> tel1 = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            Tel1 tel11 = new Tel1();
//            tel11.setId("");
//            tel11.setCreateBy("");
//            tel11.setCreateTime(new Date());
//            tel11.setUpdateBy("");
//            tel11.setUpdateTime(new Date());
//            tel11.setSysOrgCode("");
            tel11.setName("测试人员=" + name);
            tel11.setTel(i + "");
            tel11.setStatusCode("1");
            tel11.setResult("1");
            tel1.add(tel11);
        }
        return tel1;
    }


}
