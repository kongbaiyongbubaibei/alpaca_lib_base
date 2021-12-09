package com.alpaca.library_base.utils;

/**
 * @文件名: StringUtil
 * @功能描述:
 * @Date : 2018/4/23
 * @email:
 * @修改记录:
 */

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {


    public static boolean isEmpty(String str) {
        if (str == null || "null".equals(str) || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isPhoneNumber(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        String regExp = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.find();
    }

    //获取指定部分文字样式的字符串
    public static SpannableStringBuilder getColorStr(Context context, String str, String colorStr, int styleRes) {
        try {
            int fStart = str.indexOf(colorStr);
            int fEnd = fStart + colorStr.length();
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            style.setSpan(new TextAppearanceSpan(context, styleRes), fStart, fEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return style;
        } catch (Throwable throwable) {
            SpannableStringBuilder style = new SpannableStringBuilder(str);
            return style;
        }
    }


    public static boolean isIdCard(String idCard) {
        if (isEmpty(idCard)) {
            return false;
        }
        String regExp = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}[x|X|\\d]$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(idCard);
        return m.find();
    }


    public static String inputStream2String(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    public static String idCardDeal(String idCard) {
        StringBuffer sb = new StringBuffer(idCard);
        sb.replace(6, 16, "**********");
        return sb.toString();
    }

    /**
     * 四舍五入保留确定位数小数
     * @param number  原数
     * @param decimal 保留几位小数
     * @return 返回值 String类型
     */
    public static String round_down(String number, int decimal) {
        return new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_DOWN).toString();
    }

    public static boolean isNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isNumeric(str) || isDecimal(str);
    }

    /**
     * 判断字符串为纯数字
     *
     * @param str 需要判断的字符串
     * @return 是否成立
     */
    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是数字或小数
     *
     * @param str 需要判断的字符串
     * @return 是否成立
     */
    public static boolean isDecimal(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String reg = "\\d+(\\.\\d+)?";
        return str.matches(reg);
    }

    public static String getSimpleCount(long originCount) {
        DecimalFormat format = new DecimalFormat("0.#");
        //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
        format.setRoundingMode(RoundingMode.FLOOR);
        String result = "0";
        if (originCount >= 10000) {
            double temp = originCount * 1.0f / 10000;
            result = format.format(temp).concat("w");
        } else if (originCount > 1000 && originCount < 10000) {
            double temp = originCount * 1.0f / 1000;
            result = format.format(temp).concat("k");
        } else {
            result = String.valueOf(originCount);
        }
        return result;
    }
}
