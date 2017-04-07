package com.zrb.houserental.util;

import com.zrb.baseapp.tools.MyLogUtils;
import com.zrb.baseapp.tools.SystemUtils;
import com.zrb.baseapp.tools.TextUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/7.
 */

public class MyTextUtil {

    public static boolean textIsNumber(String text) {
        if (TextUtil.isEmptyString(text)) {
            return false;
        }
        try {
            double num = Double.parseDouble(text);
            if (num >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String getEndDate(String s_date, String moth) {
        DateFormat simpleDateFormat = SimpleDateFormat.getDateInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(s_date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int m = Integer.parseInt(getNumberFromString(moth));
            calendar.add(Calendar.MONTH, m);
            Date resultDate = calendar.getTime(); // 结果
            return simpleDateFormat.format(resultDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getOutOfMoth(String s_date) {
        DateFormat simpleDateFormat = SimpleDateFormat.getDateInstance();
        Date date = null;
        try {
            date = simpleDateFormat.parse(s_date);
            long old_date = date.getTime();
            long now_date = simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
            long d = now_date - old_date;
            int day = (int) (d / 1000 / 3600 / 24);
//            if (day < 0) {
//                day--;
//            }
            return day + "天";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static String totlePrice(String unit, String num) {
        try {
            double u = Double.parseDouble(getNumberFromString(unit));
            double n = Double.parseDouble(getNumberFromString(num));
            return u * n + "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String useNum(String unit, String num, String err) {
        try {
            double u = Double.parseDouble(getNumberFromString(unit));
            double n = Double.parseDouble(getNumberFromString(num));
            if (u - n < 0) {
                return err;
            }
            return u - n + "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String userPrice(String unit, String num) {
        try {
            double u = Double.parseDouble(getNumberFromString(unit));
            double n = Double.parseDouble(getNumberFromString(num));
            return u * n + "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String getNumberFromString(String unit) {
        unit = unit.trim();
        String uni2 = "";
        for (int i = 0; i < unit.length(); i++) {
            if (unit.charAt(i) >= 48 && unit.charAt(i) <= 57 || unit.charAt(i) == 46 || unit.charAt(i) == 45) {
                uni2 += unit.charAt(i);
            }
        }
        return uni2;
    }
}
