package com.lirui.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 业务需要获取对应星座
 *
 * @author Li Rui
 * @date 2020/1/8 13:48
 */
public class ConstellationUtils {

    /**
     * 十二星座
     */
    public static final String[] constellationArray = { "水瓶座", "双鱼座", "白羊座",
            "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };
    /**
     * 十二星座每月的起始日期
     */
    public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
    /**
     * 异常模板
     */
    private static final String ERROR_TEMPLATE = "不好意思，%s归属地查询的路上遇到了点问题!";
    /**
     * 星座查询异常信息
     */
    private static final String HOROSCOPE_ERROR = String.format(ERROR_TEMPLATE, "星座运势");

    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static String date2Constellation(Calendar time) {
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArray[month];
        }
        // default to return 魔羯
        return constellationArray[11];
    }
    /**
     * 根据日期获取星座
     *
     * @param time
     * @return
     */
    public static String date2Constellation(String time) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(time);
            c.setTime(date);

            String constellation = date2Constellation(c);
            System.out.println("星座：" + constellation);
            return constellation;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 查询星座运势
     *
     * @param consName
     * @param key
     * @return
     */
    public static String searchHoroscope(String consName, String key) {
        // key 的获取链接 https://www.juhe.cn/docs/api/id/58
        if (StringUtils.isBlank(consName) || StringUtils.isBlank(key)) {
            return HOROSCOPE_ERROR;
        }
        String type = "today";
        String url = "http://web.juhe.cn:8080/constellation/getAll?key=" + key + "&consName=" + consName + "&type=" + type;
        String resultData = HttpClient.doGet(url);
            JSONObject jsonObject = JSON.parseObject(resultData);
            // 查看错误码是否正常
            String errorCode = jsonObject.getString("error_code");
            if ("10001".equals(errorCode) || "10002".equals(errorCode) || "10003".equals(errorCode) || "10009".equals(errorCode)) {
                return HOROSCOPE_ERROR;
            } else if (!("0".equals(errorCode))) {
                return HOROSCOPE_ERROR;
            }
            return jsonObject.getString("summary");
    }

    /**
     * 查询星座运势
     *
     * @param consName
     * @return
     */
    public static String searchHoroscope(String consName) {
        // 100次/天
        String key = "646a9087308c551fd488ea8c7f80e3f6";
        return searchHoroscope(consName, key);
    }

    /**
     * 获取字符串中的星座并返回
     *
     * @param content 需要提取的星座的内容主体
     * @return
     */
    public static String getConsName(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }

        for(int i = 0; i < constellationArray.length; i++) {
            if(content.contains(constellationArray[i])) {
                return constellationArray[i];
            }
        }
        return null;
    }

    /**
     * 根据日期获取对应星座名称
     *
     * @param time
     * @return
     */
    public static String getConsNameByTime(String time) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(time);
            c.setTime(date);
            return date2Constellation(c);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String constellation = date2Constellation("1996-04-18 12:10:10");
//        System.out.println("星座：" + constellation);
//        System.out.println(searchHoroscope(getConsName("双鱼座的", null)));
        System.out.println(getConsNameByTime("2020-02-02 12:10:00"));
//        System.out.println(searchHoroscope(getConsName("", "2020-01-02 12:10:00")));
    }
}
