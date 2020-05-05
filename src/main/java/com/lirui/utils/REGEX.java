package com.lirui.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * JAVA 正则学习链接
 * https://www.cnblogs.com/xhj123/p/6032683.html
 */

/**
 * 对于字符串的验证拆分和替换请直接使用 String 对象自带的方法
 * 验证：boolean matches(String regex)
 * 拆分：String[] split(String regex)
 * 替换：String replaceAll(String regex, String replacement)
 * @author Li Rui
 * @date 2019/12/31 10:54
 */
public class REGEX {
    /**
     * 获取符合该正则的所有字符串
     * @param regex
     * @param str
     * @return 返回所有匹配的子串
     */
    public static List<String> getStringList(String regex, String str) {
        List<String> strs = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            strs.add(m.group());
        }
        return strs;
    }

    /**
     * 获取字符串中第一个符合的子串
     * @param regex
     * @param str
     * @return 返回字符串中第一个符合正则表达式的子串，没有返回 null
     */
    public static String getFirstSubString(String regex, String str) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.group();
        }
        return null;
    }
}
