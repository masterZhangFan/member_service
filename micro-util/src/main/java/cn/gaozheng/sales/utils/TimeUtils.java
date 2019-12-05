package cn.gaozheng.sales.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtils {
    public static  String format1 = "yyyy-MM-dd HH:mm:ss";
    public static String formatDateString(Date date) {
        if (date == null) return null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = format.format(date);
        return timeString;
    }
    /**
     * 判断给定日期是否为月末的一天
     *
     * @param date
     * @return true:是|false:不是
     */
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
    /**
     * 判断给定日期是否为月末的一天
     *
     * @param date
     * @return true:是|false:不是
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
    public static Date add(Date date,int field, int amount)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field,amount);
        return calendar.getTime();
    }
    public static Date dateFromFormatDataString(String formatDateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(formatDateString);
            return date;
        } catch (Exception ex) {

        }
        return null;
    }
    public static Date parseDate(String formatDateString,String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(formatDateString);
            return date;
        } catch (Exception ex) {

        }
        return null;
    }
    public static boolean isValidDate(String str) {
        if (str == null) return false;
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }

        return convertSuccess;
    }
    public static String format(Date date,String format){
        if (date == null ||!EmptyUtil.isNotEmpty(format)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    /**
     * 获取
     * @param startTimeStr
     * @param endTimeStr
     * @param filed
     * @return
     */
    public static List<String> getTimeStep(String startTimeStr,String endTimeStr,int filed)
    {
        Date startTime = dateFromFormatDataString(startTimeStr);
        Date endTime = dateFromFormatDataString(endTimeStr);
        String format = "yyyy-MM-dd HH:mm:ss";
        if (filed == Calendar.YEAR)
        {
            format = "yyyy";
        }
        else if (filed == Calendar.MONTH){
            format = "yyyy-MM";
        }
        else if (filed == Calendar.DATE){
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        List<String> results = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        if (startTime != null)
        {
            Date dateTemp = startTime;
            while (true)
            {
                if (simpleDateFormat.format(dateTemp).compareTo(simpleDateFormat.format(endTime)) >0) break;

                calendar.setTime(dateTemp);
                String timeStr =  simpleDateFormat.format(calendar.getTime());
                results.add(timeStr);
                calendar.add(filed, 1); // 加1 年/月/日
                dateTemp =  calendar.getTime();
            }
        }
    return results;

    }

}