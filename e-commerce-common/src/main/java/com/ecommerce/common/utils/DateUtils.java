package com.ecommerce.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * date util
 *
 * @author huizhe.yu
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String FORMAT_DATE = "yyyy-MM-dd";

    public static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static String FORMAT_MILLI_DATE_TIME = "yyyyMMddHHmmssSSS";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns =
        {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static final String datePath() {
        return getDateStrByFormat("yyyy/MM/dd");
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    public static String getDateStrByFormat(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String convertTimeToString(Long time) {
        return convertTimeToString(time, FORMAT_DATE_TIME);
    }

    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static final String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String convertTimeToString(Long time, String format) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(format);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    public static Long convertTimeToLong(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Date parseDate(String timeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
        Date date = null;
        try {
            date = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getNowDateStr() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(FORMAT_DATE));
    }

    public static String getNowDateTimeStr() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FORMAT_DATE_TIME));
    }

    public static Integer getHourSub(Date endDate) {
        return getHourSub(endDate, new Date());
    }

    public static Integer getHourSub(Date startDate, Date endDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long diff = endDate.getTime() - startDate.getTime();
        Long hour = diff % nd / nh;
        return hour.intValue();
    }

}
