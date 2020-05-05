package com.lirui.utils;

import com.alibaba.fastjson.JSON;
import com.ustcinfo.ishare.eip.admin.common.utils.HttpUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class QuartzTest {
/*    *//**
     * 本地测试群：       23614173793@chatroom
     * CloudAI通知群：	19621503528@chatroom
     *//*
    @Test
    public void test02() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<String> talkerList = new ArrayList<>();
        talkerList.add("23805279486@chatroom");
        talkerList.add("19433402967@chatroom");
        talkerList.add("18223005458@chatroom");
        String content = "关怀老用户，发现新需求，3月7日-15日，长沙和美海尔专卖店发起“24999元全城寻找海尔老用户”活动，通过老用户征集引流组建7个活动新群，参与交互2780人，老用户评选参与423人，结合微信直播秒杀在意向用户群爆破，活动目标100万，微信直播引爆93万，卡萨帝32万，卡萨帝占比34%。";
        String picUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586254839373&di=3264fd2a2882f82998d9bba2fed5dc93&imgtype=0&src=http%3A%2F%2Fupload.ccidnet.com%2F2016%2F0120%2F1453273197402.jpg";
        hashMap.put("serverKey", "lemon");
        hashMap.put("apiName", "xc");
        hashMap.put("atName", "");
        hashMap.put("atid", "");
        hashMap.put("talker", "23805279486@chatroom");
        HttpUtils.post("http://192.168.52.253:8083/lwr2server", "application/json", null, JSON.toJSONString(hashMap));
        try {

            for (String talker : talkerList) {
                hashMap.put("talker", talker);
                // 发送文本消息
                hashMap.put("type", "1");
                hashMap.put("content", content);
                HttpUtils.post("http://192.168.52.253:8083/lwr2server", "application/json", null, JSON.toJSONString(hashMap));
                // 发送图片消息
                hashMap.put("type", "2");
                hashMap.put("content", picUrl);
                HttpUtils.post("http://192.168.52.253:8083/lwr2server", "application/json", null, JSON.toJSONString(hashMap));
                Thread.sleep(3000);
                System.out.println(System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/


    @Test
    public void test01() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        Date dateStart = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateEnd = null;
        try {
            dateEnd = sdf.parse("2020-4-29");
            dateStart = sdf.parse(sdf.format(dateStart));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int freeDay = (int) ((dateEnd.getTime() - dateStart.getTime()) / 1000 / 60 / 60 / 24);
        if (freeDay < 0) {
            return;
        }
        String content = "新增-运营-运营分析驾驶舱第一版(王震)\n" +
                "新增-运营-公司测试环境和客户生产环境博保持完全一致(盛刚)\n" +
                "新增-算法商店/应用商店-可以批量用excel导入导出算法详情和应用详情(方健)\n" +
                "新增-算法商店-算法API/SDK封装方案(王涛)\n" +
                "新增-知云-新增反馈建议板块：替换原禅道模块，参考提需求的极简设计(慈龙许)\n" +
                "新增-测试：所有算法商店的API接口要做自动化测试(曹定正)\n" +
                "新增-测试-指挥棒的自动化测试(曹定正)\n" +
                "新增-机器人-平台运营指标自动推送：机器人每日/定期推送运营指标(吴彤彤)\n" +
                "新增-整体-确定平台协议(邢航)\n" +
                "新增-整体-增加预发布环境：在生产环境服务器中增加版本预发布环境，尽可能完全模拟生产环境的数据、网络、功能、业务等等(范祥峰)\n" +
                "新增-整体-增加用户行为分析：对AI平台自身访问数据、用户点击、操作等数据的分析，制定用户画像，做用户行为分析(王震)\n" +
                "新增-支持中心-帮助文档中做视频集中展示的模块，增加视频教学模块(慈龙许)\n" +
                "新增-搜索功能：增加全文搜索功能(慈龙许)\n" +
                "新增-性能-未来AIIA要使用平台开发，验证在线开发并发启动100个容器并验证可用性。(盛刚)\n" +
                "数湖：数据脱敏：手机号、身份证号等等(盛刚)\n" +
                "数湖：历史用户刷新操作，能够看到9个公用数据集(盛刚)\n" +
                "优化-首页-在应用商店标注热门以及推荐应用时要把已经推广得比较多得应用体现出来，如智能排障等。(方健)\n" +
                "优化-首页-IE不兼容时提供其他浏览器下载方式：IE浏览器，不兼容提示中谷歌和火狐，建议加上下载超链接(慈龙许)\n" +
                "优化-首页- 在平台上能否增加类似弹屏功能，引导用户操作(慈龙许)\n" +
                "优化-算法商店应用商店-我要参与新开页面：点击我要参与时新打开页面，不要在原来的页面中进行内容替换(方健)\n" +
                "优化-算法商店/应用商店-管理员修改算法信息不需要审核，也不需要下架算法(方健)\n" +
                "优化-算法商店-详情页优化：尚未上线的模型在最下方支持交流模块展示了技术文档链接，但是点击不了，要么去掉，要么给予提示(方健)\n" +
                "优化-用户账号管理-新增获取的其他账号相关信息：从OA中获取省分、联系电话、job、组织机构全称等等所有可用字段(盛刚)\n" +
                "优化-生态集市-数湖：没配智慧棒的数据集无法订阅且无法在数湖看到，但是发布后可以从总览进入，且总览更多进入的是新数湖，也看不到其他数据集(盛刚)\n" +
                "优化-模型开发平台-数据集仓库展示优化：调整操作区功能，进行统一展示优化(盛刚)\n" +
                "优化-整体：平台各个常见展示参数通过缓存的形式进行展示，降低数据得查询压力，提高性能(盛刚)\n" +
                "优化-整体-平台安全加固(登录页面部分)：对渗透测试发现的问题进行修复(盛刚)\n" +
                "优化-整体-平台安全加固(小U客服)：对渗透测试发现的问题进行修复(盛刚)\n" +
                "优化-整体-平台安全加固(nginx/tomcat)：对渗透测试发现的问题进行修复(盛刚)\n" +
                "优化-开发平台-特征工程开发-内置算法对特征进行数据去噪(盛刚)\n" +
                "优化-小U客服-对话界面模仿阿里小蜜和QQ(吴彤彤)\n" +
                "优化-可视化开发:建议在最下面显示数据10行，这样让每个步骤结果可视化(盛刚)\n" +
                "优化-举手-从通用举手图标进行举手时，也能把算法/应用/数据相关信息带过去(慈龙许)";

        content = "【版本发布倒计时】\n" +
                "本次版本发布时间：4月29日,距离上线时间还有" + freeDay + "天！！！\n" +
                "本次版本发布内容：\n" +
                content;
        hashMap.put("serverKey", "lemon");
        hashMap.put("apiName", "xc");
        hashMap.put("type", "1");
        hashMap.put("talker", "23614173793@chatroom");
        hashMap.put("content", content);
        hashMap.put("atName", "");
        hashMap.put("atid", "");
        HttpUtils.post("http://192.168.52.253:8083/lwr2server", "application/json", null, JSON.toJSONString(hashMap));
    }

    /**
     * 定时推送
     */
    @Test
    public void test02() {
String content = "数据智能PBU的小伙伴们，现在是下午6点05分，度过了一天充实的工作时光，您辛苦了，但是不要忘了下班钉钉打卡哦。";
HashMap<Object, Object> hashMap = new HashMap<>();
hashMap.put("serverKey", "lemon");
hashMap.put("apiName", "xc");
hashMap.put("type", "1");
hashMap.put("talker", "18136007108@chatroom");
hashMap.put("content", content);
hashMap.put("atName", "");
hashMap.put("atid", "");
HttpUtils.post("http://192.168.52.253:8083/lwr2server", "application/json", null, JSON.toJSONString(hashMap));
    }
}
