package org.jeecg.modules.demo.tel.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.http.HttpRequest;
import org.jeecg.modules.demo.tel.entity.CallIndexDel;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: index_del页面
 * @Author: jeecg-boot
 * @Date: 2021-03-29
 * @Version: V1.0
 */
public interface ICallIndexDelService extends IService<CallIndexDel> {


    int pull(CallIndexDel d);

    int AddMq();
}
