package com.lirui.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Li Rui
 * @date 2020/1/8 10:10
 */
public class TestClass {
    @Test
    public void test01() {
        String str = "问题##newline##回复";
        String[] split = str.split("##newline##");
        if(split.length == 2) {
            System.out.println(split[0]);
            System.out.println(split[1]);
        }
    }

    @Test
    public void test02() {
        File file = new File("C:\\Users\\SIMPLE\\Documents\\WeChat Files\\kdgcWxRobot\\FileStorage\\Image\\2020-01\\581297316a4fbaf2569f9b9d880db7ed.dat");
        System.out.println(file.exists());
    }


    @Test
    public void test03() {
        String str = "微信登出了,机器人：[botName]，请尽快重启";
        System.out.println(str.replaceAll("\\[botName\\]", "qwqwqw"));
    }

    @Test
    public void test04() {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = "wxid_39dyvh39f2k012" + simpleDateFormat.format(date1);
        System.out.println(simpleDateFormat.format(date1));
        System.out.println(toHash(str));
    }


    public static int toHash(String key) {
        // 哈希值上限
        int hashSize = 49999;
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            // 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
            int letterValue = key.charAt(i) - 96;
            // 防止编码溢出，对每步结果都进行取模运算
            hashCode = ((hashCode << 5) + letterValue) % hashSize;
        }
        return hashCode;
    }

    @Test
    public void test05() {
        String str = "\\n\\t123\\n";
        System.out.println(str.replaceAll("\\\\n", "").replaceAll("\\\\t", ""));
    }
}
