package com.lirui.utils;


import org.junit.jupiter.api.Test;

import java.awt.Font;
import java.io.File;

/**
 * @author Li Rui
 * @date 2019/12/30 20:09
 */
public class TextToPic {
    @Test
    public void test01() {
        try {
            SingleTextToPic.createImage("测试文字1\n" +
                            "测试文字2\n" +
                            "测试文字3",
                    new Font("微软雅黑", Font.PLAIN, 32),
                    new File("F:/test/a.png"), 500, 128);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        String message = "测试文字1\n" +
                "测试文字2\n" +
                "测试文字3";
        String[] strArr = message.split("\n");
        int image_width = 200;
        int image_height = 160; // 每张图片的高度
        int line_height = 50; // 每行或者每个文字的高度
        int line_num = image_height / line_height; // 每张图片有多少行文字

        String filePath = "F:/test/text-to-pic.jpg";

        try {
            MultiTextToPic.createImage(strArr, new Font("微软雅黑", Font.PLAIN, 32), image_width, image_height,  line_num, line_height, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
