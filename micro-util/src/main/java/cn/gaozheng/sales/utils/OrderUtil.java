package cn.gaozheng.sales.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 高并发情况下获取订单号
 *
 * @author
 *
 */
public final class OrderUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final AtomicInteger atomicInteger = new AtomicInteger(1000000);

    /**
     * 创建不连续的订单号
     *
     * @param no
     *            数据中心编号
     * @return 唯一的、不连续订单号
     */
    public static synchronized String getOrderNoByUUID(String no,String dateStr) {
        dateStr = dateStr.replace("-","");
        dateStr = dateStr.replace(" ","");
        dateStr = dateStr.replace(":","");
        if (dateStr.length() < 6){
            dateStr = simpleDateFormat.format(new Date());
        }
        else if (dateStr.length() < 14){
            dateStr =dateStr.substring(0,8);
            dateStr+=simpleDateFormat.format( new Date()).substring(8,13);
        }
        Date date = TimeUtils.parseDate(dateStr,"yyyyMMddHHmmss");
        if (date == null)
        {
            date = new Date();
        }
        return no +"-"+ simpleDateFormat.format(date) +"-"+ Getnum();
    }

    /**
     *
     * @return
     */
    public static String Getnum(){
        long randmonNumber = NumberUtils.genRand(9999,1);
        return String.format("%04d",randmonNumber);
    }

    /**
     * 获取同一秒钟 生成的订单号连续
     *
     * @param no
     *            数据中心编号
     * @return 同一秒内订单连续的编号
     */
    public static synchronized String getOrderNoByAtomic(String no) {
        atomicInteger.getAndIncrement();
        int i = atomicInteger.get();
        String date = simpleDateFormat.format(new Date());
        return no + date + i;
    }
}