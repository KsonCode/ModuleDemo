package com.example.kson.lib_core.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static Pattern IMG_URL = Pattern
            .compile(".*?(gif|jpeg|png|jpg|bmp)");

    private final static Pattern URL = Pattern
            .compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        return toDate(sdate, dateFormater.get());
    }

    public static Date toDate(String sdate, SimpleDateFormat dateFormater) {
        try {
            return dateFormater.parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getDateString(Date date) {
        return dateFormater.get().format(date);
    }

    public static String friendly_time2(String sdate) {
        String res = "";
        if (isEmpty(sdate))
            return "";

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String currentData = StringUtils.getDataTime("MM-dd");
        int currentDay = toInt(currentData.substring(3));
        int currentMoth = toInt(currentData.substring(0, 2));

        int sMoth = toInt(sdate.substring(5, 7));
        int sDay = toInt(sdate.substring(8, 10));
        int sYear = toInt(sdate.substring(0, 4));
        Date dt = new Date(sYear, sMoth - 1, sDay - 1);

        if (sDay == currentDay && sMoth == currentMoth) {
            res = "今天 / " + weekDays[getWeekOfDate(new Date())];
        } else if (sDay == currentDay + 1 && sMoth == currentMoth) {
            res = "昨天 / " + weekDays[(getWeekOfDate(new Date()) + 6) % 7];
        } else {
            if (sMoth < 10) {
                res = "0";
            }
            res += sMoth + "/";
            if (sDay < 10) {
                res += "0";
            }
            res += sDay + " / " + weekDays[getWeekOfDate(dt)];
        }

        return res;
    }


    /**
     * 智能格式化
     */
    public static String friendly_time3(String sdate) {
        String res = "";
        if (isEmpty(sdate))
            return "";

        Date date = StringUtils.toDate(sdate);
        if (date == null)
            return sdate;

        SimpleDateFormat format = dateFormater2.get();

        if (isToday(date.getTime())) {
            format.applyPattern(isMorning(date.getTime()) ? "上午 hh:mm" : "下午 hh:mm");
            res = format.format(date);
        } else if (isYesterday(date.getTime())) {
            format.applyPattern(isMorning(date.getTime()) ? "昨天 上午 hh:mm" : "昨天 下午 hh:mm");
            res = format.format(date);
        } else if (isCurrentYear(date.getTime())) {
            format.applyPattern(isMorning(date.getTime()) ? "MM-dd 上午 hh:mm" : "MM-dd 下午 hh:mm");
            res = format.format(date);
        } else {
            format.applyPattern(isMorning(date.getTime()) ? "yyyy-MM-dd 上午 hh:mm" : "yyyy-MM-dd 下午 hh:mm");
            res = format.format(date);
        }
        return res;
    }

    /**
     * @return 判断一个时间是不是上午
     */
    public static boolean isMorning(long when) {
        android.text.format.Time time = new android.text.format.Time();
        time.set(when);

        int hour = time.hour;
        return (hour >= 0) && (hour < 12);
    }

    /**
     * @return 判断一个时间是不是今天
     */
    public static boolean isToday(long when) {
        android.text.format.Time time = new android.text.format.Time();
        time.set(when);

        int thenYear = time.year;
        int thenMonth = time.month;
        int thenMonthDay = time.monthDay;

        time.set(System.currentTimeMillis());
        return (thenYear == time.year)
                && (thenMonth == time.month)
                && (thenMonthDay == time.monthDay);
    }

    /**
     * @return 判断一个时间是不是昨天
     */
    public static boolean isYesterday(long when) {
        android.text.format.Time time = new android.text.format.Time();
        time.set(when);

        int thenYear = time.year;
        int thenMonth = time.month;
        int thenMonthDay = time.monthDay;

        time.set(System.currentTimeMillis());
        return (thenYear == time.year)
                && (thenMonth == time.month)
                && (time.monthDay - thenMonthDay == 1);
    }

    /**
     * @return 判断一个时间是不是今年
     */
    public static boolean isCurrentYear(long when) {
        android.text.format.Time time = new android.text.format.Time();
        time.set(when);

        int thenYear = time.year;

        time.set(System.currentTimeMillis());
        return (thenYear == time.year);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 返回long类型的今天的日期
     *
     * @return
     */
    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        String curDate = dateFormater2.get().format(cal.getTime());
        curDate = curDate.replace("-", "");
        return Long.parseLong(curDate);
    }

    public static String getCurTimeStr() {
        Calendar cal = Calendar.getInstance();
        String curDate = dateFormater.get().format(cal.getTime());
        return curDate;
    }

    /***
     * 计算两个时间差，返回的是的秒s
     *
     * @param dete1
     * @param date2
     * @return
     * @author 火蚁 2015-2-9 下午4:50:06
     */
    public static long calDateDifferent(String dete1, String date2) {

        long diff = 0;

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = dateFormater.get().parse(dete1);
            d2 = dateFormater.get().parse(date2);

            // 毫秒ms
            diff = d2.getTime() - d1.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return diff / 1000;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || TextUtils.isEmpty(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断一个url是否为图片url
     *
     * @param url
     * @return
     */
    public static boolean isImgUrl(String url) {
        if (url == null || url.trim().length() == 0)
            return false;
        return IMG_URL.matcher(url).matches();
    }

    /**
     * 判断是否为一个合法的url地址
     *
     * @param str
     * @return
     */
    public static boolean isUrl(String str) {
        if (str == null || str.trim().length() == 0)
            return false;
        return URL.matcher(str).matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    public static String getString(String s) {
        return s == null ? "" : s;
    }

    /**
     * 将一个InputStream流转换成字符串
     *
     * @param is
     * @return
     */
    public static String toConvertString(InputStream is) {
        StringBuffer res = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader read = new BufferedReader(isr);
        try {
            String line;
            line = read.readLine();
            while (line != null) {
                res.append(line + "<br>");
                line = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != isr) {
                    isr.close();
                    isr.close();
                }
                if (null != read) {
                    read.close();
                    read = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
            }
        }
        return res.toString();
    }

    /***
     * 截取字符串
     *
     * @param start 从那里开始，0算起
     * @param num   截取多少个
     * @param str   截取的字符串
     * @return
     */
    public static String getSubString(int start, int num, String str) {
        if (str == null) {
            return "";
        }
        int leng = str.length();
        if (start < 0) {
            start = 0;
        }
        if (start > leng) {
            start = leng;
        }
        if (num < 0) {
            num = 1;
        }
        int end = start + num;
        if (end > leng) {
            end = leng;
        }
        return str.substring(start, end);
    }

    /**
     * 获取当前时间为每年第几周
     *
     * @return
     */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }

    /**
     * 获取当前时间为每年第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
        week = week == 0 ? 52 : week;
        return week > 0 ? week : 1;
    }

    public static int[] getCurrentDate() {
        int[] dateBundle = new int[3];
        String[] temp = getDataTime("yyyy-MM-dd").split("-");

        for (int i = 0; i < 3; i++) {
            try {
                dateBundle[i] = Integer.parseInt(temp[i]);
            } catch (Exception e) {
                dateBundle[i] = 0;
            }
        }
        return dateBundle;
    }

    /**
     * 返回当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * Unicode解码方法
     *
     * @param ori
     * @return
     */
    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }

    /**
     * 意思很简单:
     * 如果是省略尾部，就把头部长度设置为总长度，尾部长度设置为0
     * 如果是省略头部，就把尾部长度设置为总长度，头部长度设置为0
     * 如果是省略中间，就把头部和尾部长度设置为想要的长度
     * @param strSource
     * @param headLength
     * @param tailLength
     * @return
     */
    public static String convertStr2Ellipsis(String strSource, int headLength, int tailLength) {
        if (TextUtils.isEmpty(strSource)) return strSource;
        if (tailLength < 0 || headLength < 0) return strSource;
        if (tailLength <= 0 && headLength <= 0) return strSource;
        String strEllipsis = "...";
        // 获取总长度
        int length = strSource.length();
        if (tailLength == 0) {
            if (headLength < length) {
                return strSource.substring(0, headLength) + strEllipsis;
            } else {
                return strSource;
            }
        } else if (headLength == 0) {
            if (tailLength < length) {
                return strEllipsis + strSource.substring(length - tailLength);
            } else {
                return strSource;
            }
        } else {
            if (tailLength + headLength < length) {
                return strSource.substring(0, headLength) + strEllipsis + strSource.substring(length - tailLength);
            } else {
                return strSource;
            }
        }
    }
}
