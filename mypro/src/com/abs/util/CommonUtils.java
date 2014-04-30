package com.abs.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class CommonUtils {

    /**************************************************************/
    /*************************** 判断 ******************************/
    /**************************************************************/

    /**
     * 判断一个List是否为空.
     * 
     * @param list
     * @return
     */
    public final static boolean isNotEmptyList(List<?> list) {
        boolean result = false;
        if (list != null && list.size() > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 根据传入对象返回字符串
     * 
     * @param obj
     * @return
     */
    public final static String toString(Object obj) {
        if (obj != null) {
            return obj.toString();
        } else {
            return null;
        }
    }

    /**
     * 判断一个字符串是否为空.
     * 
     * @param str
     * @return
     */
    public final static boolean isNotEmptyStr(String str) {
        boolean result = false;
        if (str != null && !"".equals(str.trim())) {
            result = true;
        }
        return result;
    }

    /**************************************************************/
    /*************************** 转换 ******************************/
    /**************************************************************/
    /**
     * 日期转字符串.
     * 
     * @param date
     * @param pattern
     *            日期格式
     * @return
     */
    public final static String dateToString(java.util.Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 日期转字符串(默认格式为yyyy-MM-dd).
     * 
     * @param date
     * @return
     */
    public final static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }

    /**
     * 字符串转日期.
     * 
     * @param str
     * @return
     */
    public final static Date stringToDate(String str) {
        return stringToDate(str, "yyyy-MM-dd");
    }

    /**
     * 字符串转日期.
     * 
     * @param str
     * @param patten
     *            日期格式
     * @return
     */
    public final static Date stringToDate(String str, String patten) {
        if (str == null || "".equals(str)) {
            return null;
        }
        DateFormat format = new SimpleDateFormat(patten);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // date = java.sql.Date.valueOf(str);

        return date;
    }

    /**
     * <P><B>说明：</B>描述这个方法的作用</P>
     * <P><B>日期：</B>2013-9-3 上午9:11:50</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param str
     * @return
     */
    public static BigDecimal stringToBigDecimal(String str) {
        if (isNotEmptyStr(str)) {
            return new BigDecimal(str);
        }
        return null;
    }

    /**
     * 判断字符串是否为空,并转换成Long型.如果为空返回null.
     * 
     * @param str
     * @return
     */
    public final static Long stringToLong(String str) {
        if (isNotEmptyStr(str)) {
            return Long.parseLong(str);
        }
        return null;
    }

    /**
     * 判断字符串是否为空,并转换成Double型.如果为空返回null.
     * 
     * @param str
     * @return
     */
    public final static Double stringToDouble(String str) {
        if (isNotEmptyStr(str)) {
            return Double.parseDouble(str);
        }
        return null;
    }

    /**
     * 判断字符串是否为空,并转换成Integer型.如果为空返回null.
     * 
     * @param str
     * @return
     */
    public final static Integer stringToInteger(String str) {
        if (isNotEmptyStr(str)) {
            return Integer.parseInt(str);
        }
        return null;
    }

    /**************************************************************/
    /*************************** 比较 ******************************/
    /**************************************************************/
    /**
     * 比较两个Long是否相等.
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean longEquals(Long value1, Long value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否大于value2
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean longBiggerThan(Long value1, Long value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否小于value2.
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean longSmallerThan(Long value1, Long value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否大于等于value2.
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean longBiggerEqualsThan(Long value1, Long value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否小于等于value2.
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean longSmallerEqualsThan(Long value1, Long value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个Integer是否相等.
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean intEquals(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否大于value2
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean intBiggerThan(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否大于等于value2.
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean intBiggerEqualsThan(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否小于value2
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean intSmallerThan(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) < 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较value1是否小于等于value2.
     * 
     * @param value1
     * @param value2
     * @return
     */
    public final static boolean intSmallerEqualsThan(Integer value1, Integer value2) {
        if (value1 == null || value2 == null) {
            return false;
        }
        if (value1.compareTo(value2) <= 0) {
            return true;
        }
        return false;
    }

    /**************************************************************/
    /*************************** 计算 ******************************/
    /**************************************************************/

    /**
     * 乘法(如果是浮点型需要输入scale).
     * 
     * @param value1
     *            支持Integer,Long,Double
     * @param value2
     *            支持Integer,Long,Double
     * @param scale
     *            浮点型需要输入scale
     * @return
     */
    public final static <T> Object multiply(Object value1, Object value2, int scale) {
        if (value1 == null || value2 == null) {
            return null;
        }
        BigDecimal b1 = null;
        BigDecimal b2 = null;
        if (value1 instanceof Integer) {
            b1 = new BigDecimal((Integer) value1);
        } else if (value1 instanceof Long) {
            b1 = new BigDecimal((Long) value1);
        } else if (value1 instanceof Double) {
            b1 = new BigDecimal((Double) value1);
        } else {
            return null;
        }
        if (value2 instanceof Integer) {
            b2 = new BigDecimal((Integer) value2);
        } else if (value2 instanceof Long) {
            b2 = new BigDecimal((Long) value2);
        } else if (value2 instanceof Double) {
            b2 = new BigDecimal((Double) value2);
        } else {
            return null;
        }
        return new Double(b1.multiply(b2).divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    /**
     * 除法.
     * 
     * @param value1
     *            支持Integer,Long,Double
     * @param value2
     *            支持Integer,Long,Double
     * @param scale
     *            精确度(小数点后几位)
     * @return 返回double
     */
    public final static Double divide(Object value1, Object value2, int scale) {
        if (value1 == null || value2 == null) {
            return null;
        }
        BigDecimal b1 = null;
        BigDecimal b2 = null;
        if (value1 instanceof Integer) {
            b1 = new BigDecimal((Integer) value1);
        } else if (value1 instanceof Long) {
            b1 = new BigDecimal((Long) value1);
        } else if (value1 instanceof Double) {
            b1 = new BigDecimal((Double) value1);
        } else {
            return null;
        }
        if (value2 instanceof Integer) {
            b2 = new BigDecimal((Integer) value2);
            if (isZero(b2)) {
                return null;
            }
        } else if (value2 instanceof Long) {
            b2 = new BigDecimal((Long) value2);
            if (isZero(b2)) {
                return null;
            }
        } else if (value2 instanceof Double) {
            b2 = new BigDecimal((Double) value2);
            if (isZero(b2)) {
                return null;
            }
        } else {
            return null;
        }
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private final static boolean isZero(BigDecimal decimalValue) {
        return BigDecimal.ZERO.compareTo(decimalValue) == 0;
    }

    /**
     * 获得UUID.
     * 
     * @return
     */
    public static final String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * <P><B>说明：</B>获得指定长度的UUID</P>
     * <P><B>日期：</B>2013-5-14 上午8:39:31</P>
     * <P><B>作者：</B>zhangjun</P>
     * @param number 需要获得的UUID数量
     * @return UUID数组
     */
    public static String getUUID(int number) {
        String result = "";
        if (number < 1) {
            return null;
        }
        if (number <= 32) {
            result = getUUID();
        } else {
            int time = (number / 32) + 1;
            StringBuffer total = new StringBuffer();
            for (int i = 0; i < time; i++) {
                total.append(getUUID());
            }
            result = total.toString();
        }
        return result.substring(0, number);
    }
}
