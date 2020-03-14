package com.lirui.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Li Rui
 * @date 2020/1/1 20:41
 */
public class DataTest {
    @Test
    public void test01() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        // 获取今日日期
        String nowDate = df.format(new Date());
        String time = "2019-01-02 12:10:00";
        time = df.format(df.parse(time));
        System.out.println(time);
        System.out.println(nowDate.compareTo(time));
    }

    /**
     * 分别获取日期中的年月日
     */
    @Test
    public void Test02() {
        Date dt=new Date();

        String year=String.format("%tY", dt);

        String mon=String .format("%tm", dt);

        String day=String .format("%td", dt);

        System.out.println(year);

        System.out.println(mon);

        System.out.println(day);
    }

    @Test
    public void test03() {
        String createTime = "1580950948000";
        long time = Long.parseLong(createTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd号HH点mm分");
        Date date = new Date();
        date.setTime(time);
        String createDate = simpleDateFormat.format(time);
        System.out.println(createDate);
    }

    @Test
    public void test04() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test05() {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse("2020-02-16");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;
            System.out.println(cal.get(Calendar.DAY_OF_WEEK));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
