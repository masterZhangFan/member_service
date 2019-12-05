package cn.gaozheng.sales.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeOffsetHelper {
    String minitesOffset30 = "30";
    String hourOffset08 = "08";

    String dayOffset25 = "25";

    String monthOffset12 = "12";





    public static final String FormatType00000000 ="00000000";
    public static final String FormatType00000830 ="00000830";
    public static final String FormatType00000900 ="00000900";
    public static final String FormatType00250000 ="00250000";
    public static final String FormatType00250830 ="00250830";
    public static final String FormatType00250900 ="00250900";
    public static final String FormatType12250000 ="12250000";
    public static final String FormatType12250830 ="12250830";
    public static final String FormatType12250900 ="12250900";

    public static String _TimeOffset_Start(String formartType,String inTime)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date time = null;
        if (formartType.equals(FormatType00000000)){
            return inTime.substring(0,10) + " 00:00:00";
        }
        else if (formartType.equals(FormatType00000830)){
            return inTime.substring(0,10) + " 08:30:00";
        }
        else if (formartType.equals(FormatType00000900)){
            return inTime.substring(0,10) + " 09:00:00";
        }
        else if (formartType.equals(FormatType00250000)){
            String timeStr = inTime.substring(0,7) + "-25 00:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.MONTH,-1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00250830)){
            String timeStr = inTime.substring(0,7) + "-25 08:30:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.MONTH,-1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00250900)){
            String timeStr = inTime.substring(0,7) + "-25 09:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.MONTH,-1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType12250000)){
            String timeStr = inTime.substring(0,4) + "-12-25 00:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.YEAR,-1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType12250900)){
            String timeStr= inTime.substring(0,4) + "-12-25 09:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.YEAR,-1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType12250830)){
            String timeStr= inTime.substring(0,4) + "-12-25 08:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.YEAR,-1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        return null;
    }
    //_TimeOffset_End
    public static String _TimeOffset_End(String formartType,String inTime)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date time = null;
        if (formartType.equals(FormatType00000000)){
            String timeStr = inTime.substring(0,10) + " 00:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.DATE,1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00000830)){
            String timeStr =  inTime.substring(0,10) + " 08:30:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.DATE,1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00000900)){
            String timeStr =  inTime.substring(0,10) + " 09:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.DATE,1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00250000)){
            String timeStr = inTime.substring(0,7) + "-25 00:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                return simpleDateFormat.format(time);
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00250830)){
            String timeStr = inTime.substring(0,7) + "-25 08:30:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                return simpleDateFormat.format(time);
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00250900)){
            String timeStr = inTime.substring(0,7) + "-25 09:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                return simpleDateFormat.format(time);
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType12250000)){
            String timeStr = inTime.substring(0,4) + "-12-25 00:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                return simpleDateFormat.format(time);
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType12250830)){
            String timeStr = inTime.substring(0,4) + "-12-25 08:30:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                return simpleDateFormat.format(time);
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType12250900)){
            String timeStr= inTime.substring(0,4) + "-12-25 09:00:00";
            try{
                time  = simpleDateFormat.parse(timeStr);
                return simpleDateFormat.format(time);
            }
            catch (Exception ex)
            {

            }
        }
        return null;
    }
    //_TimeOffset_Backward

    /**
     * 此功能向前偏移,如00000830，时间减去8小时30分钟，00250000时间减去25天
     * @param formartType
     * @param inTime
     * @return
     */
    public static String _TimeOffset_Backward(String formartType,String inTime)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date time = null;

        if (formartType.equals(FormatType00000000))
        {
            String timeStr = inTime;
            return timeStr;
        }
        else if (formartType.equals(FormatType00000830))
        {
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.HOUR,-8);
                calendar.add(Calendar.MINUTE,-30);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00000900))
        {
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.HOUR,-9);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        if (formartType.equals(FormatType00250000)){
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.HOUR,-((25-1)*24+0));
                calendar.add(Calendar.MONTH,1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00250830)){
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.HOUR,-((25-1)*24+8));
                calendar.add(Calendar.MINUTE,-30);
                calendar.add(Calendar.MONTH,1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00250900)){
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.HOUR,-((25-1)*24+9));
                calendar.add(Calendar.MONTH,1);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }

        return null;
    }
    //_TimeOffset_Forward
    public static String _TimeOffset_Forward(String formartType,String inTime)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date time = null;

        if (formartType.equals(FormatType00000000))
        {
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                return simpleDateFormat.format(time);
            }
            catch (Exception ex)
            {

            }

        }
        else if (formartType.equals(FormatType00000830))
        {
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.HOUR,-8);
                calendar.add(Calendar.MINUTE,-30);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        else if (formartType.equals(FormatType00000900))
        {
            String timeStr = inTime;
            try{
                time  = simpleDateFormat.parse(timeStr);
                calendar.setTime(time);
                calendar.add(Calendar.HOUR,-9);
                return simpleDateFormat.format(calendar.getTime());
            }
            catch (Exception ex)
            {

            }
        }
        return null;
    }

    public static Date DayStart(Date time,Integer hourOffset,Integer minuteOffset){
         //开始时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        if (minuteOffset != null) {
            calendar.add(Calendar.MINUTE,-minuteOffset);
        }
        if (hourOffset != null) {
            calendar.add(Calendar.HOUR,-hourOffset);
        }

        String startTime = String.format("%s %02d:%02d:00",TimeUtils.format(calendar.getTime(),"yyyy-MM-dd"),hourOffset!=null?hourOffset:0,minuteOffset!=null?minuteOffset:0);
        return TimeUtils.dateFromFormatDataString(startTime);
    }
    public static Date DayEnd(Date time, Integer hourOffset,Integer minitesOffset){
        Date startTime = TimeOffsetHelper.DayStart(time,hourOffset,minitesOffset);
        return TimeUtils.add(startTime,Calendar.DATE,1);
    }
    public static Date MonthStart(Date time, Integer dayOffset, Integer hourOffset,Integer minuteOffset){
        //开始时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        if (minuteOffset != null) {
            calendar.add(Calendar.MINUTE,-minuteOffset);
        }
        if (hourOffset != null) {
            calendar.add(Calendar.HOUR,-hourOffset);
        }
        if (dayOffset != null){
            calendar.add(Calendar.DATE,-dayOffset);
        }
        Date time2 = calendar.getTime();
        String startTime = String.format("%s-%02d %02d:%02d:00",TimeUtils.format(time2,"yyyy-MM"),dayOffset!=null?dayOffset:1,hourOffset!=null?hourOffset:0,minuteOffset!=null?minuteOffset:0);
        return TimeUtils.dateFromFormatDataString(startTime);
    }
    public static Date MonthEnd(Date time,Integer dayOffset, Integer hourOffset,Integer minuteOffset){
        Date startTime = TimeOffsetHelper.MonthStart(time,dayOffset,hourOffset,minuteOffset);
        return TimeUtils.add(startTime,Calendar.MONTH,1);
    }
    public static Date YearStart(Date time,Integer monthOffset,Integer dayOffset, Integer hourOffset,Integer minuteOffset){
        //开始时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        if (minuteOffset != null) {
            calendar.add(Calendar.MINUTE,-minuteOffset);
        }
        if (hourOffset != null) {
            calendar.add(Calendar.HOUR,-hourOffset);
        }
        if (dayOffset != null){
            calendar.add(Calendar.DATE,-dayOffset);
        }
        if (monthOffset != null){
            calendar.add(Calendar.MONTH,-monthOffset);
        }
        String startTime = String.format("%s-%02d-%02d %02d:%02d:00",TimeUtils.format(calendar.getTime(),"yyyy"),monthOffset!=null?monthOffset:01,dayOffset!=null?dayOffset:01,hourOffset!=null?hourOffset:0,minuteOffset!=null?minuteOffset:0);
        return TimeUtils.dateFromFormatDataString(startTime);
    }
    public static Date YearEnd(Date time,Integer monthOffset,Integer dayOffset, Integer hourOffset,Integer minuteOffset){
        Date startTime = TimeOffsetHelper.MonthStart(time,dayOffset,hourOffset,minuteOffset);
        return TimeUtils.add(startTime,Calendar.YEAR,1);
    }
}
