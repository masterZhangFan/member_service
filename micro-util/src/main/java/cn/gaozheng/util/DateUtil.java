package cn.gaozheng.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Date Util
 *
 * @author Cheng Bo
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    public static final int MONTH_LENGTH = 12;
    public static final String TIME_ZONE = "GMT+8";
    public static final String PATTERN_DAY_1 = "yyyy.MM.dd";
    public static final String DEFAULT_FORMAT_PATTERN_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DEFAULT_FORMAT_PATTERN_WEEK = "yyyy-ww";
    public static final String DEFAULT_FORMAT_PATTERN_MONTH = "yyyy-MM";
    public static final String DEFAULT_FORMAT_PATTERN_YEAR = "yyyy";
    public static final String DEFAULT_FORMAT_PATTERN_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_PATTERN_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_FORMAT_PATTERN_DAY = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_PATTERN_HOURS = "HH:mm:ss";
    public static final String DEFAULT_FORMAT_PATTERN_MILLISECOND_TOKEN = "yyyyMMddHHmmssSSS";

    public static final int DAY_MILLISECOND = 1000 * 60 * 60 * 24;

    private static volatile DateUtil dateUtil = null;

    public static DateUtil getInstance() {
        if (dateUtil == null) {
            synchronized (DateUtil.class) {
                dateUtil = new DateUtil();
            }
        }

        return dateUtil;
    }

    /**
     * 格式化日期
     *
     * @param localDateTime LocalDateTime
     * @param pattern       格式
     * @return 格化式后的字符串
     */
    public String format(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) return null;
        Assert.notNull(localDateTime, "The first arg could not be null!");
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化日期
     *
     * @param localDate LocalDate
     * @param pattern   格式
     * @return 格化式后的字符串
     */
    public String format(LocalDate localDate, String pattern) {
        if (localDate == null) return null;
        Assert.notNull(localDate, "The first arg could not be null!");
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public String format(Date date, String pattern) {
        Assert.notNull(date, "The first arg could not be null!");
        Assert.notNull(pattern, "The second arg could not be null!");
        return format(toLocalDateTime(date), pattern);
    }

    public LocalDateTime toLocalDateTime(Date date) {
        Assert.notNull(date, "The date could not be null!");
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public LocalDate toLocalDate(Date date) {
        Assert.notNull(date, "The date could not be null!");
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalTime toLocalTime(Date date) {
        check(date);
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        check(localDateTime);
        return Date.from(toInstant(localDateTime));
    }

    public static Date toDate(LocalDate localDate) {
        check(localDate);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static Instant toInstant(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant;
    }

    public static void check(Object param) {
        Assert.notNull(param, "The param could not be null!");
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间的LocalDateTime示例
     */
    public static LocalDateTime nowLdt() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间的LocalDateTime示例
     */
    public static LocalTime nowLt() {
        return LocalTime.now(ZoneId.systemDefault());
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间的LocalDateTime示例
     */
    public static LocalDate nowLd() {
        return LocalDate.now(ZoneId.systemDefault());
    }

    public static List<String> betweenMonths(int sMonth, int eMonth) {
        if (sMonth < 1 || eMonth > 12) {
            throw new IllegalArgumentException("eMonth must not less than 1 and eMonth must not greater than 12");
        }
        if (eMonth < sMonth) {
            throw new IllegalArgumentException("eMonth must greater than or equal to sMonth");
        }
        List<String> monthList = new ArrayList<>(eMonth - sMonth + 1);

        for (; sMonth <= eMonth; sMonth++) {
            monthList.add(String.format("%02d", sMonth));
        }
        return monthList;
    }

    public static List<String> betweenYears(int sYear, int eYear) {
        if (eYear < sYear) {
            throw new IllegalArgumentException("eYear must greater than or equal to sYear");
        }

        List<String> yearList = new ArrayList<>(eYear - sYear + 1);

        for (; sYear <= eYear; sYear++) {
            yearList.add(String.format("%d", sYear));
        }
        return yearList;
    }

    public static LocalDateTime parseLdt(String date, String pattern) {

        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLd(String date, String pattern) {

        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parseLt(String time, String pattern) {

        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }
}
