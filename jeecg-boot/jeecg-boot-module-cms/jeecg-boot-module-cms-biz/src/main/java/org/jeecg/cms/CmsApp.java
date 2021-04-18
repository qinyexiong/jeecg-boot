package org.jeecg.cms;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroBeanAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {ShiroAnnotationProcessorAutoConfiguration.class, ShiroAutoConfiguration.class, ShiroBeanAutoConfiguration.class})
@EnableFeignClients
public class CmsApp {

    public static void main(String[] args) {
        SpringApplication.run(CmsApp.class, args);
    }
}
