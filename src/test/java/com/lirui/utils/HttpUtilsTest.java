package com.lirui.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * HttpUtils 测试
 * 需要注意的是可能会报 java.lang.NullPointerException
 * 最好做空的判断
 */
@Slf4j
public class HttpUtilsTest {
//    @Test
//    public void test01() {
//        String url = "http://v.juhe.cn/calendar/day?date=2020-3-21&key=8fca5f04cf55b74ebf3936da6d12f361";
//        try {
//            Response response = HttpUtils.get(url, null);
//            String resultDate = response.body().string();
//            JSONObject jsonObject = JSON.parseObject(resultDate);
//            log.debug("error_code:" + jsonObject.getString("error_code"));
//            JSONObject result = jsonObject.getJSONObject("result");
//            JSONObject data = result.getJSONObject("data");
//            String suit = data.getString("suit");
//            log.debug("suit:" + suit);
//        } catch (IOException e) {
//            log.error(ExceptionUtils.getStackTrace(e));
//        }
//    }
//
//    @Test
//    public void test02() {
//        String url = "http://127.0.0.1:8201/lwr/push";
//        try {
//            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("serverKey", "lemon");
//            hashMap.put("apiName", "xc");
//            hashMap.put("type", "1");
//            hashMap.put("talker", "23614173793@chatroom");
//            hashMap.put("content", "你好");
//            hashMap.put("atName", "");
//            hashMap.put("atid", "");
//            Response response = HttpUtils.post(url, "", null, JSON.toJSONString(hashMap));
//            String resultDate = response.body().string();
//            JSONObject jsonObject = JSON.parseObject(resultDate);
//            String code = jsonObject.getString("code");
//            log.info("code:" + code);
//        } catch (IOException e) {
//            log.error(ExceptionUtils.getStackTrace(e));
//        }
//    }

    @Test
    public void test03() {
        log.info("接口调用：" + fun01());
    }

    public static String fun01() {
String ERROR = "调用接口成功";
try {
    Response response = HttpUtils.get("http://v1.alapi.cn/api/soul", null);
    if (response.isSuccessful()) {
        return response.body().string();
    } else {
        return "接口调用失败";
    }
} catch (IOException e) {
}
return "接口调用失败";
    }

//    @Test
//    public void test04() {
//        Response response = null;
//        try {
//            response = HttpUtils.get("http://v1.alapi.cn/api/soul", null);
//            log.info(response.body().string());
//            response.close();
//        } catch (IOException e) {
//            if(response != null) {
//                response.close();
//            }
//        }
//    }
}
