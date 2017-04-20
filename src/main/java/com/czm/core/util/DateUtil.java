package com.czm.core.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by CHENZHANMEI on 2017/3/12.
 */
public final class DateUtil {


    private final static String SS = "yyyy-MM-dd HH:mm:ss";

    private final static String DD = "yyyy-MM-dd";


    public static String format(Date date, String pattern) {

        if (null == date) return null;

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String formatDay(Date date) {

        return format(date, SS);
    }


    public static Date parse(String time, String pattern) {

        if (StringUtils.isEmpty(time) || StringUtils.isEmpty(pattern))
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDay(String time) {
        return parse(time, DD);
    }

    public static Date parse(String time) {
        return parse(time, SS);
    }

    /**
     * 加 天
     *
     * @param time
     * @param value
     * @return
     */
    public static Date addDay(Date time, int value) {

        return addDate(time, Calendar.DATE, value);
    }

    /**
     * 根据传入的时间类型 添加对应的时间
     *
     * @param time
     * @param field
     * @param value
     * @return
     */
    public static Date addDate(Date time, int field, int value) {
        if (ObjectUtils.isEmpty(time))
            return null;

        if (0 == value)
            return time;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(field, value);
        return calendar.getTime();
    }

    /**
     * 增加 分钟
     *
     * @param time
     * @param value
     * @return
     */
    public static Date addMinute(Date time, int value) {
        return addDate(time, Calendar.MINUTE, value);
    }

    /**
     * 增加 月
     *
     * @param time
     * @param value
     * @return
     */
    public static Date addMonth(Date time, int value) {
        return addDate(time, Calendar.MONTH, value);
    }


//    public static int get



}
