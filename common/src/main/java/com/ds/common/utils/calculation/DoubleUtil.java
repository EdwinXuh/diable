package com.ds.common.utils.calculation;

import java.math.BigDecimal;

/**
 * @author raptor
 */
public class DoubleUtil {
    // 小数点后的保留位数
    private static final int DEF_DIV_SCALE = 2;

    /**
     * Double精确的加法运算
     *
     * @param d1 被加数
     * @param d2 加数
     * @return 两个参数的和
     */
    public static double add(double d1, double d2) {
        BigDecimal value1 = new BigDecimal(Double.toString(d1));
        BigDecimal value2 = new BigDecimal(Double.toString(d2));
        return value1.add(value2).doubleValue();
    }

    /**
     * Double精确的减法运算
     *
     * @param d1 被减数
     * @param d2 减数
     * @return 两个参数的差
     */
    public static double sub(double d1, double d2) {
        BigDecimal value1 = new BigDecimal(Double.toString(d1));
        BigDecimal value2 = new BigDecimal(Double.toString(d2));
        return value1.subtract(value2).doubleValue();
    }

    /**
     * Double精确的乘法运算
     *
     * @param d1 被乘数
     * @param d2 乘数
     * @return 两个参数的积
     */
    public static Double mul(double d1, double d2) {
        BigDecimal value1 = new BigDecimal(Double.toString(d1));
        BigDecimal value2 = new BigDecimal(Double.toString(d2));
        return value1.multiply(value2).doubleValue();
    }

    /**
     * Double精确的除法运算, 当出现除不尽的情况时, 精确到小数点以后10位, 以后的数字四舍五入
     *
     * @param d1 被除数
     * @param d2 除数
     * @return 两个参数的商
     */
    public static double div(double d1, double d2) {
        return div(d1, d2, DEF_DIV_SCALE);
    }

    /**
     * Double精确的除法运算, 当出现除不尽的情况时, 精确到小数点以后10位, 以后的数字四舍五入
     *
     * @param d1    被除数
     * @param d2    除数
     * @param scale 表示需要精确到小数点的后几位
     * @return 两个参数的商
     */
    public static double div(double d1, double d2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("参数[scale]必须是正整数或者零");
        }
        BigDecimal value1 = new BigDecimal(Double.toString(d1));
        BigDecimal value2 = new BigDecimal(Double.toString(d2));
        return value1.divide(value2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
