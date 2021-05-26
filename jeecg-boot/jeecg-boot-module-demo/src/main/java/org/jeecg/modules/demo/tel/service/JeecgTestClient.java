package org.jeecg.modules.demo.tel.service;

import org.jeecg.common.api.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//jeecg-cloud-demo的服务名是 jeecg-demo
@FeignClient(value = "jeecg-cms")
@Component
public interface JeecgTestClient {
    @PostMapping(value = "/cms/sources/getFeign")
    Result<String> getFeign();
}