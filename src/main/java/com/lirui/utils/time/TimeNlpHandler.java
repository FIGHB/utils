package com.lirui.utils.time;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liuchengbiao
 * @date 2019-12-13 10:41
 */
public class TimeNlpHandler {

    private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";

    static {
        try {
            URL url = TimeNormalizer.class.getResource("/TimeExp.m");
            TimeNormalizer normalizer = new TimeNormalizer(url.toURI().toString());
            normalizer.setPreferFuture(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析时间
     *
     * @param input
     * @return
     */
    public static TimeUnit[] extract(String input) {
        TimeNormalizer normalizer = new TimeNormalizer();
        TimeUnit[] timeUnit = normalizer.parse(input);
        return timeUnit;
    }

    /**
     * 默认只提取第一个时间
     *
     * @param input
     * @return
     */
    public static String extractOne(String input) {
        TimeNormalizer normalizer = new TimeNormalizer();
        TimeUnit[] timeUnit = normalizer.parse(input);
        if (timeUnit == null || timeUnit.length == 0) {
            return null;
        }
        String timeValue = formatDate(timeUnit[0].getTime(), defaultDatePattern);
        return timeValue;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        TimeUnit[] timeUnits = extract("明天上午9点23");
        for (TimeUnit timeUnit : timeUnits) {
            System.out.println(formatDate(timeUnit.getTime(), defaultDatePattern));
        }
    }

}
