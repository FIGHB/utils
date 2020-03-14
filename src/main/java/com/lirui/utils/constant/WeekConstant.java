package com.lirui.utils.constant;

/**
 * @author Li Rui
 * @date 2020/1/3 14:51
 */
public enum WeekConstant {
    SUNDAY("星期天 周日", "SUN"),
    MONDAY("星期一 周一", "MON"),
    TUESDAY("星期二 周二", "TUE"),
    WEDNESDAY("星期三 周三", "WED"),
    THURSDAY("星期四 周四", "THU"),
    FRIDAY("星期五 周五", "FRI"),
    SATURDAY("星期六 周六", "SAT");
    private String desc;
    private String value;
    WeekConstant(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public String getValue() {
        return value;
    }
}
