package com.czm.core.util;

import java.util.regex.Pattern;

/**
 * Created by CHENZHANMEI on 2017/6/6.
 * 用于校验各种数据
 */
public class ValidUtil {

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{1,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 校验用户名
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {

        return null != username && Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return null != password && Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return mobile != null && Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return email != null && Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return chinese != null && Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return idCard != null && Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return url != null && Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return ipAddr != null && Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    public static void main(String[] args) {
        boolean username = isUsername("ab");
        System.out.println(username);
    }

}
