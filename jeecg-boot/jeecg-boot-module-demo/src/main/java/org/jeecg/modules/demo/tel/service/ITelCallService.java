package org.jeecg.modules.demo.tel.service;

import org.jeecg.modules.demo.tel.entity.TelCall;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 手机空号检测
 * @Author: jeecg-boot
 * @Date: 2021-02-04
 * @Version: V1.0
 */
public interface ITelCallService extends IService<TelCall> {

    void jiance();
}
