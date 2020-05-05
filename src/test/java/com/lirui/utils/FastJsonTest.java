package com.lirui.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * fastjson 使用测试，其各个版本之间不兼容所以需要引入指定的版本
 *
 * 需要引入依赖
 *  <dependency>
 *  <groupId>com.alibaba</groupId>
 *  <artifactId>fastjson</artifactId>
 *  <version>1.2.47</version>
 *  </dependency>
 */
@Slf4j
public class FastJsonTest {
    @Test
    public void test01() {
        String resultData = "{\"code\": 200, \"errMsg\": \"错误消息\"}";
        JSONObject jsonObject = JSON.parseObject(resultData);
        if(jsonObject == null) {
            log.error("解析失败");
            return;
        }
        String errMsg = jsonObject.getString("errMsg");
        log.info("errMsg:" + errMsg);
    }
}
