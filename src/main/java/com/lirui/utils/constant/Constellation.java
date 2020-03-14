package com.lirui.utils.constant;

/**
 * @author Li Rui
 * @date 2020/1/1 18:40
 */
public enum Constellation {

    ARIES("白羊座"),
    TAURUS("金牛座"),
    GEMINI("双子座"),
    CACER("巨蟹座"),
    LEO("狮子座"),
    VIRGO("处女座"),
    LIBRA("天秤座"),
    SCORPIO("天蝎座"),
    SAGITTARIUS("射手座"),
    CAPRICOM("摩羯座"),
    AQUARIUS("水瓶座"),
    PISCES("双鱼座");

    private String desc;
    Constellation(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
