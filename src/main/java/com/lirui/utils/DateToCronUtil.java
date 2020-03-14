package com.lirui.utils;

import com.lirui.utils.constant.WeekConstant;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将用户内容转换成cron表达式(做定时任务)
 *
 * @ClassName DateToCronUtil
 * @Description
 * @Date 2018/12/25 15:18
 * @Aurhor liang.hao
 * @email liang.hao@ustcinfo.com
 */
public class DateToCronUtil {
    private static final String TRANS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TRANS_CRON_FORMAT_ONCE = "ss mm HH dd MM ? yyyy";
    private static final String TRANS_CRON_FORMAT_EVERY_DAY = "ss mm HH 1/ * ? *";
    private static final String TRANS_CRON_FORMAT_EACH_WEEK = "ss mm HH ? * -- *";
    private static final String TRANS_CRON_FORMAT_EACH_MONTH = "ss mm HH dd 1/1 ? *";

    public static void main(String[] args) throws Exception {
//        String result0 = getCron("day", "2018-08-11 12:11:00");
//        String result1 = getCron("week", "2018-08-11 12:11:00");
//        String result2 = getCron("month", "2019-01-01 12:00:00");
//        String result3 = getCronByOnce("2017-01-01 12:12:12");
//        String result4 = getCronToDate("12 12 12 01 01/1 ? 2018");
//        System.out.println(result0);
//        System.out.println(result1);
//        System.out.println(result2);
//        System.out.println(result3);
//        System.out.println(result4);
//        System.out.println(getCronToDate("00 11 12 11/1 2 ? 2018"));
        System.out.println(DateToCronUtil.getCronExpress("每天", "2019-01-06 09:00:10"));
        System.out.println(getCronExpress("每个周末提醒我", "2020-01-05 12:11:00"));
//        System.out.println(DateToCronUtil.getCronExpress("工作日", sf.format(new Date())));
        System.out.println(DateToCronUtil.getCronExpress("每月", "2020-01-05 08:50:00"));
    }

    /**
     * @return java.lang.String
     * @Author liang.hao
     * @Description 生成只执行一次的corn表达式
     * @Date 15:27 2018/12/25
     * @Param [dateStr]
     **/
    public static String getCronByOnce(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date parse = format.parse(dateStr);
        return fmtDateToStr(parse, TRANS_CRON_FORMAT_ONCE);
    }


    /**
     * 根据任务内容获取相应的 cron 表达式
     *
     * @param input 任务内容
     * @param startDateStr 起始时间
     * @return
     */
    public static String getCronExpress(String input, String startDateStr) {
        try {
            if (input.indexOf("每天") != -1 || input.indexOf("天天") != -1 || input.indexOf("每日") != -1 || input.indexOf("日日") != -1 || input.indexOf("每一天") != -1) {
                // 每天一次
                return getCron("everyDay", startDateStr);
            } else if (input.indexOf("每周") != -1 || input.indexOf("每个周") != -1 || input.indexOf("周周") != -1 || input.indexOf("每一周") != -1 || input.indexOf("每星期") != -1 || input.indexOf("工作日") != -1 || input.indexOf("双休") != -1) {
                // 每周
                String weekCron = getCron("eachWeek", startDateStr);
                String cron = getWeekToWeekCron(input, weekCron);
                return cron != null ? cron : getWeekCron(input, weekCron);
            } else if (input.indexOf("每月") != -1 || input.indexOf("月月") != -1 || input.indexOf("每个月") != -1) {
                // 每月
                return getCron("eachMonth", startDateStr);
            } else {
                // 暂时不支持
                return getCronByOnce(startDateStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将【周几到周几】转换成相应的 cron 中的子表达式
     * 例如： 周一到周三 转换成 MON-WED
     *
     * @param input
     * @param cron
     * @return
     */
    private static String getWeekToWeekCron(String input, String cron) {
        if(StringUtils.isBlank(input) || StringUtils.isBlank(cron)) {
            return null;
        }
        // 是否是工作日
        if(input.matches("(.*?)(工作日|周一到周五)(.*?)")) {
            return cron.replaceAll("eachWeek", "MON-FRI");
        }
        // 是否是周末
        if(input.matches("(.*?)(周末|周六周日|周六到周日|双休日)(.*?)")) {
            return cron.replaceAll("eachWeek", "SAT-SUN");
        }
        // 是周几到周几
        if(input.matches("(.*?)[周|星期][1-7|一|二|三|四|五|六|天|日][到|至|~][周|星期][1-7|一|二|三|四|五|六|天|日](.*?)")) {
            String startDate = null;
            String endDate = null;
            for(WeekConstant week : WeekConstant.values()) {
                String[] strArr = week.getDesc().split(" ");
                // 提取输入的内容中包含的起始和结束时间并转换成相应的 cron 表达式中的对应值
                for (String subStr : strArr) {
                    if(input.indexOf(subStr) == -1) {
                        continue;
                    }
                    if (startDate == null) {
                        startDate = week.getValue();
                        input.replace(subStr, " ");
                    } else {
                        endDate = week.getValue();
                        break;
                    }
                }
            }
            return cron.replaceAll("eachWeek", startDate+ "-" +endDate);
        }
        return null;
    }

    /**
     * 根据 input 判断是每周哪几天，并将 cron 表达式中的 week 替换成相应的内容
     *
     * @param input
     * @return
     */
    public static String getWeekCron(String input, String cron) {
        if(StringUtils.isBlank(input) || StringUtils.isBlank(cron)) {
            return null;
        }
        StringBuffer weekCron = new StringBuffer();
        while(input.matches("(.*?)[周|星期][一|二|三|四|五|六|天|日](.*?)")) {
            for(WeekConstant week : WeekConstant.values()) {
                String[] strArr = week.getDesc().split(" ");
                // 提取输入的内容中包含的起始和结束时间并转换成相应的 cron 表达式中的对应值
                for (String subStr : strArr) {
                    if(input.indexOf(subStr) == -1) {
                        continue;
                    }
                    if(weekCron.length() > 0) {
                        weekCron.append(",");
                    }
                    weekCron.append(week.getValue());
                    input.replace(subStr, " ");
                }
            }
            if(weekCron != null) {
                return cron.replaceAll("eachWeek", weekCron.toString());
            }
        }
        return null;
    }

    /**
     * @return java.lang.String
     * @Author liang.hao
     * @Description 生成每月或每周或每天执行的cron
     * @Date 15:27 2018/12/25
     * @Param [period, startDateStr]
     **/
    public static String getCron(String period, String startDateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date startDate = format.parse(startDateStr);
        StringBuffer cronStringBuffer = new StringBuffer();
        if ("eachMonth".equals(period)) {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_EACH_MONTH).trim();
            cronStringBuffer.append(startDateCron);
        } else if ("everyDay".equals(period)) {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_EVERY_DAY).trim();
            cronStringBuffer.append(startDateCron).insert(cronStringBuffer.lastIndexOf("/") + 1, "1");
        } else if ("eachWeek".equals(period)) {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_EACH_WEEK).trim();
            cronStringBuffer.append(startDateCron.replaceAll("--", period));
        }
        return cronStringBuffer.toString();
    }

    /**
     * @return java.lang.String
     * @Author liang.hao
     * @Description 将一个cron转换成时间格式
     * @Date 15:27 2018/12/25
     * @Param [cron]
     **/
    public static String getCronToDate(String cron) {
        String format = null;
        StringBuffer stringBuffer = new StringBuffer(cron);
        int lastIndexOf = stringBuffer.lastIndexOf("/");
        stringBuffer.deleteCharAt(lastIndexOf);
        stringBuffer.deleteCharAt(lastIndexOf);
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date date = sdf.parse(stringBuffer.toString());
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = sdf.format(date);
        } catch (Exception e) {
            return null;
        }
        return format;
    }

    /**
     * @return java.lang.String
     * @Author liang.hao
     * @Description 格式转换
     * @Date 15:28 2018/12/25
     * @Param [date, dtFormat]
     **/
    public static String fmtDateToStr(Date date, String dtFormat) {
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }
}
