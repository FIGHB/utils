package com.lirui.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Li Rui
 * @date 2019/12/31 10:07
 */
public class RegexTest {
    @Test
    public void test01() {
        String str = "你好88812345678987";
        String regex = "(888|588|688|468|568|668|768|868|968)[0-9]{9}";
        System.out.println(REGEX.getStringList(regex, str));
    }

    /**
     * 申通快递，正则表达式
     */
    @Test
    public void test02() {
        // 888123456789321 11123321123456
        String str = "查快递：1198765412312";
        String regex = "(888|588|688|468|568|668|768|868|968)[0-9]{9}|(11|22)[0-9]{10}|(STO)[0-9]{10}|(37|33|11|22|44|55|66|77|88|99)[0-9]{11}|(4)[0-9]{11}";
        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    @Test
    public void test03() {
        String str = "帮我查快递：1198765412312";
        String regex = "查(.*?)快递(.*?)[0-9]";
        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    @Test
    public void test04() {
//        String regex = "(问题-)(.*?)(答案-)([^/]+)";
//        String regex = "\\+[^/]+";
        String str = "###问题###回复";
        String[] split = str.split("###");
        if(split.length == 2) {
            String str1 = split[0];
        }
        System.out.println(split.length);
//        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    /**
     * 通用快递单号过滤，粗略提取
     */
    @Test
    public void test05() {
        String regex = "[a-zA-Z|0-9]{8,15}";
        String str = "圆通快递：YT4321488662716";
        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    /**
     * 文本过滤
     */
    @Test
    public void test06() {
        String regex = "(.*?)定时任务(.*?)";
        String str = "添加定时任务123";
        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    @Test
    public void test07() {
        System.out.println("123工作日123".matches("(.*?)[工作日|每周一到周五|除双休日]+(.*?)"));
        System.out.println(REGEX.getFirstSubString("[工作日|每周一到周五|除双休日]+", "123工作日123"));
    }

    @Test
    public void test08() {
        String regex = "(.*?)[周|星期][1-7|一|二|三|四|五|六|天|日][到|至|~][周|星期][1-7|一|二|三|四|五|六|天|日](.*?)";
        System.out.println("每周二~周四".matches(regex));
    }

    @Test
    public void test09() {
//        String regex = "(.*?)[(工作日)|(周一到周五)|(除双休日)]+(.*?)";
        String regex = "(.*?)(工作日|周一到周五|除双休日)(.*?)";
        String str = "啊啊啊每周一到周五啊啊啊";
        System.out.println(REGEX.getFirstSubString(regex, str));
        System.out.println("啊啊啊每周一到周五啊啊啊".matches(regex));
    }

    @Test
    public void test10() {
        String regex = "[0-9]*@chatroom";
        String str = "24063350538@chatroom";
        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    @Test
    public void test11() {
        String regex = "[(提醒)|(通知)](我)?";
        String str = "明天提醒我开会通知我吃饭";
        System.out.println(str.replaceAll(regex,""));
    }

    @Test
    public void test12() {
        String regex = "[(提醒)|(通知)](我)?[^/]+";
        String str = "明天提醒我开会";
        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    //(.*?)定时(.*?)提醒(.*?)
    @Test
    public void test13() {
        String regex = "(.*?)(配置|新增)(.*?)(提问|问题|问答|语料)(.*?)";
        String str = "aaa新增啊啊问答";
        System.out.println(REGEX.getFirstSubString(regex, str));
    }

    @Test
    public void test14() {
        String regex = "(.*?)(祝福语|贺词)(.*?)";
        String str = "aaa最近祝福语asd";
        System.out.println(REGEX.getFirstSubString(regex, str));
        System.out.println(str.matches(regex));
    }

    // (.*?)(添加|新增)?(定时)?(提醒|任务|闹铃)(.*?)
    @Test
    public void test15() {
        String regex = "(.*?)定时(.*?)(提醒|任务)(.*?)";
        String str = "aaa定时任务";
        System.out.println(REGEX.getFirstSubString(regex, str));
        System.out.println(str.matches(regex));
    }

    @Test
    public void test16() {
        String regex = "(.*?)疫情$";
        String str = "南京疫情";
        System.out.println(REGEX.getFirstSubString(regex, str));
        System.out.println(str.matches(regex));
    }

    @Test
    public void test17() {
        String regex = "(\\[CQ:at,qq(.*?)])";
        String str = "[CQ:at,qq=1272776156] [CQ:image,file=8C8B21EFF56AAB71986D1025381BCCED.png,url=https://gchat.qpic.cn/gchatpic_new/839521927/1065030950-2714063967-8C8B21EFF56AAB71986D1025381BCCED/0?term=2]";
        List<String> stringList = REGEX.getStringList(regex, str);
        System.out.println(stringList);
    }

    @Test
    public void test18() {
        String regex = "(\\[CQ:at,qq(.*?)])";
        String str = "[CQ:at,qq=1272776156]".replaceAll("\\[CQ:at,qq=", "").replaceAll("]", "");
        System.out.println(str);
    }

    @Test
    public void test19() {
        String regex = "(/\\*ADMIN-LOGIN-FONT-COLOR\\*/(.*?)/\\*ADMIN-LOGIN-FONT-SIZE\\*/)";
        String str = ".admin-login{/*ADMIN-LOGIN-FONT-COLOR*/color:yellow;/*ADMIN-LOGIN-FONT-SIZE*/font-size:12px;}";
        String subStr = str.replaceAll(regex, "/* ADMIN-LOGIN-FONT-COLOR */color:red;/* ADMIN-LOGIN-FONT-SIZE */");
        System.out.println(subStr);
    }
}
