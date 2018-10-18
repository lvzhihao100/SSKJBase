package com.sskj.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lv on 18-6-4.
 */

public class TimeFormatUtil {
    public static String FORMAT_A = "yyyy.MM.dd HH:mm";
    public static String FORMAT_B = "yyyy-MM-dd";
    public static String FORMAT_C = "HH:mm MM/dd";
    public static String FORMAT_D = "mm分ss秒";
    public static String FORMAT_E = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_F = "yyyy-MM-dd HH:mm";
    public static String FORMAT_G = "MM-dd HH:mm";
    public static String FORMAT_H = "HH:mm:ss";
    public static String FORMAT_I = "yyyy年MM月";
    public static String FORMAT_J = "yyyy/MM/dd HH:mm:ss";


    public static SimpleDateFormat SF_FORMAT_A = new SimpleDateFormat(FORMAT_A);
    public static SimpleDateFormat SF_FORMAT_B = new SimpleDateFormat(FORMAT_B);
    public static SimpleDateFormat SF_FORMAT_C = new SimpleDateFormat(FORMAT_C);
    public static SimpleDateFormat SF_FORMAT_D = new SimpleDateFormat(FORMAT_D);
    public static SimpleDateFormat SF_FORMAT_E = new SimpleDateFormat(FORMAT_E);
    public static SimpleDateFormat SF_FORMAT_F = new SimpleDateFormat(FORMAT_F);
    public static SimpleDateFormat SF_FORMAT_G = new SimpleDateFormat(FORMAT_G);
    public static SimpleDateFormat SF_FORMAT_H = new SimpleDateFormat(FORMAT_H);
    public static SimpleDateFormat SF_FORMAT_I = new SimpleDateFormat(FORMAT_I);
    public static SimpleDateFormat SF_FORMAT_J = new SimpleDateFormat(FORMAT_J);

    public static String formatA(Long time) {
        return SF_FORMAT_A.format(new Date(time));
    }

    public static String formatSix2Five(String time) {
        return time.substring(0, time.length() - 3);
    }

    //将该种时间格式的时间转为时间戳  yyyy-MM-dd HH:mm:ss
    public static String parseLongE(String time) {
        try {
            return (SF_FORMAT_E.parse(time).getTime() / 1000) + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String parseLongB(String time) {
        try {
            return (SF_FORMAT_B.parse(time).getTime())+"";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String limitMaxDate(Date date) {
        int day = (int) (date.getTime() / (1000 * 60 * 60 * 24));
        int nowDay = (int) (System.currentTimeMillis() / (1000 * 60 * 60 * 24));
        if (day >= nowDay) {
            return SF_FORMAT_B.format(Calendar.getInstance().getTime());
        } else {
            return SF_FORMAT_B.format(date);

        }
    }
}
