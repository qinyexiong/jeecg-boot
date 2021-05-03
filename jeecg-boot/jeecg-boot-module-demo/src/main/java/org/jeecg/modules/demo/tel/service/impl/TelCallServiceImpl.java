package org.jeecg.modules.demo.tel.service.impl;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.demo.tel.entity.TelCall;
import org.jeecg.modules.demo.tel.mapper.TelCallMapper;
import org.jeecg.modules.demo.tel.service.ITelCallService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description: 手机空号检测
 * @Author: jeecg-boot
 * @Date: 2021-02-04
 * @Version: V1.0
 */
@Service
public class TelCallServiceImpl extends ServiceImpl<TelCallMapper, TelCall> implements ITelCallService {


    //检测
    @Override
    public void jiance() {

        String url = "https://mobileempty.shumaidata.com/mobileempty";
        String appCode = "755652064d2b4bacbbf856b13a3e7877";


        List<TelCall> telCallEntityList = this.list();
        String id = null;
        for (TelCall user : telCallEntityList) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("userid=" + user.getId());
            id = user.getId();
            String name = user.getName();
            String tel = user.getTel();
            Map<String, String> params = new HashMap<>();
            params.put("mobile", tel);
            if (StringUtils.isNotBlank(user.getResult())) {
                continue;
            }
            String result = "{\"msg\":\"成功\",\"success\":true,\"code\":200,\"data\":{\"order_no\":\"066465039918522180\",\"area\":\"湖南-长沙\",\"channel\":\"中国移动\",\"status\":1}}";
//            String result = "{\"msg\":\"不支持的号段\",\"success\":false,\"code\":400,\"data\":{}}";
//            try {
//                result = get(appCode, url, params);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            Map map = JSON.parseObject(result, Map.class);
            String code = map.get("code").toString();
            if ("200".equals(code)) {
                String data = map.get("data").toString();
                Map res = JSON.parseObject(data, Map.class);
                String status = res.get("status").toString();

                System.out.println(status + "##############  第" + id + "数据检测完成");
//                0:空号；1:实号；2:停机；3:库无；4:沉默号；5:风险号  1000:未检测 ;
                if ("0".equals(status)) {
                    user.setStatusCode(status);
                    user.setResult("空号");
                }
                if ("1".equals(status)) {
                    user.setStatusCode(status);
                    user.setResult("实号");
                }
                if ("2".equals(status)) {
                    user.setStatusCode(status);
                    user.setResult("停机");
                }
                if ("3".equals(status)) {
                    user.setStatusCode(status);
                    user.setResult("库无");
                }
                if ("4".equals(status)) {
                    user.setStatusCode(status);
                    user.setResult("沉默号");
                }
                if ("5".equals(status)) {
                    user.setStatusCode(status);
                    user.setResult("风险号");
                }
            } else {
                user.setResult(result);
            }
            this.updateById(user);
        }
    }


    /**
     * 用到的HTTP工具包：okhttp 3.13.1
     * <dependency>
     * <groupId>com.squareup.okhttp3</groupId>
     * <artifactId>okhttp</artifactId>
     * <version>3.13.1</version>
     * </dependency>
     */
    public String get(String appCode, String url, Map<String, String> params) throws IOException {
        url = url + buildRequestUrl(params);
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).build();
        Response response = client.newCall(request).execute();
        System.out.println("返回状态码" + response.code() + ",message:" + response.message());
        String result = response.body().string();
        return result;
    }

    public static String buildRequestUrl(Map<String, String> params) {
        StringBuilder url = new StringBuilder("?");
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        return url.toString().substring(0, url.length() - 1);
    }


}
