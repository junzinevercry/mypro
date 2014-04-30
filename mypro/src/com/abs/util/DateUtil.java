package com.abs.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**************************************************************/
    /*************************** 比较 ******************************/
    /**************************************************************/
    /** 精确到年. */
    public static Integer COMPARE_TYPE_YEAR = new Integer(0);
    /** 精确到月. */
    public static Integer COMPARE_TYPE_MONTH = new Integer(1);
    /** 精确到日. */
    public static Integer COMPARE_TYPE_DAY = new Integer(2);
    /** 精确到小时. */
    public static Integer COMPARE_TYPE_HOUR = new Integer(3);
    /** 精确到分钟. */
    public static Integer COMPARE_TYPE_MINUTE = new Integer(4);
    /** 精确到秒. */
    public static Integer COMPARE_TYPE_SECOND = new Integer(5);
    public static Long DAY_TIME = 60L * 60L * 24L * 1000L;

    /**
     * 比较两个日期是否相等.
     * 
     * @param date1
     * @param date2
     * @param compareType
     * @return
     */
    public static boolean dateEquals(Date date1, Date date2, Integer compareType) {
        if (date1 == null || date2 == null) {
            return false;
        }
        date1 = changeType(date1, compareType);
        date2 = changeType(date2, compareType);
        if (date1.compareTo(date2) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较date1是否在date2之前.
     * 
     * @param date1
     * @param date2
     * @param compareType
     * @return
     */
    public static boolean dateBefore(Date date1, Date date2, Integer compareType) {
        if (date1 == null || date2 == null) {
            return false;
        }
        date1 = changeType(date1, compareType);
        date2 = changeType(date2, compareType);
        if (date1.before(date2)) {
            return true;
        }
        return false;
    }

    /**
     * 比较date1是否在date2之后.
     * 
     * @param date1
     * @param date2
     * @param compareType
     * @return
     */
    public static boolean dateAfter(Date date1, Date date2, Integer compareType) {
        if (date1 == null || date2 == null) {
            return false;
        }
        date1 = changeType(date1, compareType);
        date2 = changeType(date2, compareType);
        if (date1.after(date2)) {
            return true;
        }
        return false;
    }

    /**
     * 比较date1是否不超过date2.
     * 
     * @param date1
     * @param date2
     * @param compareType
     * @return
     */
    public static boolean dateNotAfter(Date date1, Date date2, Integer compareType) {
        if (date1 == null || date2 == null) {
            return false;
        }
        date1 = changeType(date1, compareType);
        date2 = changeType(date2, compareType);
        if (date1.after(date2)) {
            return false;
        }
        return true;
    }

    /**
     * 比较date1是否不在date1之前.
     * 
     * @param date1
     * @param date2
     * @param compareType
     * @return
     */
    public static boolean dateNotBefore(Date date1, Date date2, Integer compareType) {
        if (date1 == null || date2 == null) {
            return false;
        }
        date1 = changeType(date1, compareType);
        date2 = changeType(date2, compareType);
        if (date1.before(date2)) {
            return false;
        }
        return true;
    }

    private static Date changeType(Date date, Integer compareType) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        if (COMPARE_TYPE_SECOND.compareTo(compareType) > 0) {
            c.set(Calendar.SECOND, 0);
            if (COMPARE_TYPE_MINUTE.compareTo(compareType) > 0) {
                c.set(Calendar.MINUTE, 0);
                if (COMPARE_TYPE_HOUR.compareTo(compareType) > 0) {
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    if (COMPARE_TYPE_DAY.compareTo(compareType) > 0) {
                        c.set(Calendar.DAY_OF_MONTH, 1);
                        if (COMPARE_TYPE_MONTH.compareTo(compareType) > 0) {
                            c.set(Calendar.MONTH, 0);
                        }
                    }
                }
            }
        }
        return c.getTime();
    }

    /**************************************************************/
    /*************************** 计算 ******************************/
    /**************************************************************/
    /**
     * 得到一个日期之前几天的日期.
     * 
     * @param date
     * @param before
     * @return
     */
    public static Date getDateBefore(Date date, long before) {
        long lTime = date.getTime();
        lTime -= before * DAY_TIME;
        Date result = new Date(lTime);
        return result;
    }

    /**
     * 得到一个日期之前后天的日期.
     * 
     * @param date
     * @param before
     * @return
     */
    public static Date getDateAfter(Date date, long after) {
        long lTime = date.getTime();
        lTime += after * DAY_TIME;
        Date result = new Date(lTime);
        return result;
    }

    /**
     * 得到下个月的今天.
     * 
     * @param date
     * @return
     */
    public static Date getNextMonthDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        return c.getTime();
    }

    /**
     * 得到上个月的今天.
     * 
     * @param date
     * @return
     */
    public static Date getPrevMonthDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        return c.getTime();
    }

    /**
     * 得到之后若干个月的第一天.
     * 
     * @param date
     * @param afterMonth
     * @return
     */
    public static Date getFirstDayOfAfterMonth(Date date, int afterMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + afterMonth);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 得到之前若干个月的第一天.
     * 
     * @param date
     * @param nextMonth
     * @return
     */
    public static Date getFirstDayOfBeforeMonth(Date date, int beforeMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - beforeMonth);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 得到之后若干个月的最后一天.
     * 
     * @param date
     * @param afterMonth
     *            若干个月,当月为0
     * @return
     */
    public static Date getLastDayOfAfterMonth(Date date, int afterMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + afterMonth + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        return c.getTime();
    }

    /**
     * 得到之前若干个月的最后一天.
     * 
     * @param date
     * @param beforeMonth
     *            若干个月,当月为0
     * @return
     */
    public static Date getLastDayOfBeforeMonth(Date date, int beforeMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - beforeMonth + 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        return c.getTime();
    }

    /**
     * 得到后一天.
     * 
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        return c.getTime();
    }

    /**
     * 得到前一天.
     * 
     * @param date
     * @return
     */
    public static Date getPrevDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        return c.getTime();
    }

    public static boolean isWeekend(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)) {
            return true;
        }
        return false;
    }

    public static Long countDateBelong(Date date1, Date date2) {
        date1 = changeType(date1, COMPARE_TYPE_DAY);
        date2 = changeType(date2, COMPARE_TYPE_DAY);
        Long result = null;
        if (dateBefore(date1, date2, COMPARE_TYPE_DAY)) {
            result = (date2.getTime() - date1.getTime()) / DAY_TIME;
        } else {
            result = (date1.getTime() - date2.getTime()) / DAY_TIME;
        }
        return result + 1;
    }

    public static Integer getWeekends(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static String getWeekendsStr(Date date) {
        Integer week = getWeekends(date);
        if (week == null) {
            return "";
        }
        if (Calendar.MONDAY == week) {
            return "星期一";
        } else if (Calendar.TUESDAY == week) {
            return "星期二";
        } else if (Calendar.WEDNESDAY == week) {
            return "星期三";
        } else if (Calendar.THURSDAY == week) {
            return "星期四";
        } else if (Calendar.FRIDAY == week) {
            return "星期五";
        } else if (Calendar.SATURDAY == week) {
            return "星期六";
        } else if (Calendar.SUNDAY == week) {
            return "星期日";
        }
        return "";
    }

    /**
     * <P><B>说明：</B>两个日期的差值</P>
     * <P><B>日期：</B>2013-10-17 下午02:21:25</P>
     * <P><B>作者：</B>Jichen</P>
     * @param date1
     * @param date2
     * @return
     */
    public static long getDifferenceValue(Date date1, Date date2) {

        return date2.getTime() - date1.getTime();

    }

    /**
     * <P><B>说明：</B>返回两个日期相差小时数</P>
     * <P><B>日期：</B>2013-10-17 下午02:47:29</P>
     * <P><B>作者：</B>Jichen</P>
     * @param date1
     * @param date2
     * @return
     */
    public static int getDHourValue(Date date1, Date date2) {

        long dValue = getDifferenceValue(date1, date2);

        return Long.valueOf(dValue / (1000 * 60 * 60)).intValue();

    }

    public static int getHourTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

}
