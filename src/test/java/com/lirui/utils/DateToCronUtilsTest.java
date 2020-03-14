package com.lirui.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Li Rui
 * @date 2020/1/3 11:04
 */
public class DateToCronUtilsTest {
    private static final String TRANS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sf;
    static {
        sf = new SimpleDateFormat(TRANS_DATE_FORMAT);

    }

    @Test
    public void test01() {
//        System.out.println(DateToCronUtil.getCronExpress("每天", sf.format(new Date())));
//        System.out.println(DateToCronUtil.getCronExpress("工作日", sf.format(new Date())));
        System.out.println(DateToCronUtil.getCronExpress("每月", "2020-01-05 08:50:00"));
    }
}
