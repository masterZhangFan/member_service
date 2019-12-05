package cn.gaozheng.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Number Util
 *
 * @author Cheng Bo
 * @version 1.0
 */
public final class NumberUtil {

    public static final Integer INT_NAG_ONE = -1;
    public static final Integer INT_ZERO = 0;
    public static final Integer INT_ONE = 1;
    public static final Integer INT_TWO = 2;
    public static final Integer INT_THREE = 3;
    public static final Long  LONG_ZERO = 0L;


    public static final Double DOUBLE_ZERO = 0D;

    /**
     * d1 / d2
     *
     * @param d1    The dividend
     * @param d2    The divisor
     * @param scale scale of the {@code BigDecimal} quotient to be returned.
     * @return {@code d1 / d2}
     */
    public static Double divide(Double d1, Double d2, int scale) {
        Assert.notNull(d1, "The d1 must not be null ");
        Assert.notNull(d2, "The d2 must not be null ");
        if (d2 == 0) {
            throw new IllegalArgumentException(
                    "The d2 must not equals zero");
        }

        Assert.notNull(d1, "");
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(d1.toString());
        BigDecimal b2 = new BigDecimal(d2.toString());
        return divide(b1, b2, scale).doubleValue();
    }

    /**
     * b1 / b2
     *
     * @param b1    The dividend
     * @param b2    The divisor
     * @param scale scale of the {@code BigDecimal} quotient to be returned.
     * @return {@code b1 / b2}
     */
    public static BigDecimal divide(BigDecimal b1, BigDecimal b2, int scale) {
        Assert.notNull(b1, "The d1 must not be null ");
        Assert.notNull(b2, "The d2 must not be null ");
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }
    //乘法
    public static BigDecimal multiply(Double  value1,Double value2){
        BigDecimal v1 = new BigDecimal(Double.toString(value1));
        BigDecimal v2 = new BigDecimal(Double.toString(value2));
        return v1.multiply(v2);
    }
    public static BigDecimal multiply(Long  value1,Double value2){
        BigDecimal v1 = new BigDecimal(Long.toString(value1));
        BigDecimal v2 = new BigDecimal(Double.toString(value2));
        return v1.multiply(v2);
    }
    public static BigDecimal multiply(Double  value1,Long value2){
        BigDecimal v1 = new BigDecimal(Double.toString(value1));
        BigDecimal v2 = new BigDecimal(Long.toString(value2));
        return v1.multiply(v2);
    }
    //
    /**
     * 提供精确的加法运算
     *
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static Double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     * @param v1
     *            被減数
     * @param v2
     *            減数
     * @return两个参数的差
     */
    public static Double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    public static BigInteger sub(long v1, long v2) {
        BigInteger b1 = new BigInteger(Double.toString(v1));
        BigInteger b2 = new BigInteger(Double.toString(v2));
        return b1.subtract(b2);
    }
    /**
     * 提供精确的乘法运算
     *
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     * @param v1
     *            被除數
     * @param v2
     *            除數
     * @param scale
     *            表示表示需要精確到小數點以後位数。
     * @return 兩個參數的商
     */
    public static BigDecimal div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * d1 - d2
     *
     * @param d1 first value
     * @param d2 value to be subtracted from d1
     * @return {@code d1 - d2}
     */
    public static Double subtract(Double d1, Double d2) {
        Assert.notNull(d1, "The d1 must not be null ");
        Assert.notNull(d2, "The d2 must not be null ");
        BigDecimal b1 = new BigDecimal(d1.toString());
        BigDecimal b2 = new BigDecimal(d2.toString());
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 提供精確的小數位四捨五入處理。
     * 提供精确的小数位四舍五入处理
     *
     * @param v
     *            需要四捨五入的數位
     * @param scale
     *            小數點後保留幾位
     * @return 四捨五入後的結果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static Double scale(Double d1, int scale) {
        if(d1==null)return 0.0;
        BigDecimal bg = new BigDecimal(d1);
        return bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(scale(0.02343,2));
    }
    public static BigDecimal scale(BigDecimal d1, int scale) {
        if(d1==null)return BigDecimal.ZERO;
        else{
            return d1.setScale(scale, BigDecimal.ROUND_HALF_UP);
        }
    }

}
