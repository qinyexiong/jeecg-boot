package org.jeecg.modules.demo.tel1.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.tel1.entity.Tel1;

/**
 * @Description: 测试表
 * @Author: jeecg-boot
 * @Date: 2021-03-26
 * @Version: V1.0
 */
public interface ITel1Service extends IService<Tel1> {

    void save1(Tel1 tel1);
}
