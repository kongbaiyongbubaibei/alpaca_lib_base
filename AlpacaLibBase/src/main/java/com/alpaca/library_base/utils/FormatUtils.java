package com.alpaca.library_base.utils;

import android.annotation.SuppressLint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * class desc :
 * <p>
 * author : zzh
 * date : 2020/2/17
 */
public class FormatUtils {

    public static void main(String[] args) {
        System.out.println(numFormatToWan(29800, "w"));
    }

    /**
     * 数字转换为以万为单位的字符串
     */
    public static String numFormatToWan(long num, String unit) {
        if (num < 10000) {
            return String.valueOf(num);
        }
        String numStr = new DecimalFormat("#.0").format(num / 10000d);
        String[] ss = numStr.split("\\.");
        if ("0".equals(ss[1])) {
            return ss[0] + unit;
        } else if ('0' == ss[1].charAt(0)) {
            return ss[0] + unit;
        } else {
            return ss[0] + "." + ss[1].charAt(0) + unit;
        }
    }


    @SuppressLint("DefaultLocale")
    public static String videoTimeFormat(int time) {
        int seconds = time % 60;
        int minutes = time / 60 % 60;
        int hours = time / 3600;
        String result;
        if (hours > 0) {
            result = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            result = String.format("%02d:%02d", minutes, seconds);
        }
        return result;
    }

    /**
     * 数字转换为以万为单位的字符串
     */
    public static String numFormatToThousand(long num, String unit) {
        if (num < 1000) {
            return String.valueOf(num);
        }
        String numStr = new DecimalFormat("#.0").format(num / 1000d);
        String[] ss = numStr.split("\\.");
        if ("0".equals(ss[1])) {
            return ss[0] + unit;
        } else if ('0' == ss[1].charAt(0)) {
            return ss[0] + unit;
        } else {
            return ss[0] + "." + ss[1].charAt(0) + unit;
        }
    }

    public static String numFormatToString(long num) {
        if (num >= 10000) {
            return FormatUtils.numFormatToWan(num, "w");
        } else if (num >= 1000) {
            return FormatUtils.numFormatToThousand(num, "k");
        } else {
            return String.valueOf(num);
        }
    }

    private final static char[][] LEADING_DECIMALS = new char[][]{
            "0.".toCharArray(), "0.0".toCharArray(),
            "0.00".toCharArray(), "0.000".toCharArray(), "0.0000".toCharArray(),
            "0.00000".toCharArray(),
            "0.000000".toCharArray(), "0.0000000".toCharArray(), "0.00000000".toCharArray(),
            "0.000000000".toCharArray(), "0.0000000000".toCharArray(), "0.00000000000".toCharArray(),
            "0.000000000000".toCharArray(), "0.0000000000000".toCharArray(),
            "0.00000000000000".toCharArray(),
            "0.000000000000000".toCharArray()
    };

    /**
     * format a double value quickly, will remove the suffix:0
     */
    public static String fastFormat(double d, int precision) {
        int posPrecision = Math.abs(precision);
        double roundUpVal = Math.abs(d) * Math.pow(10d, posPrecision) + 0.5d;
        if (roundUpVal > 999999999999999d || posPrecision > 16) {// double has max 16 precisions
            return bigDecFormat(d, posPrecision);
        }
        long longPart = (long) Math.nextUp(roundUpVal);
        if (longPart < 1) {
            return "0";
        }
        char[] longPartChars = Long.toString(longPart).toCharArray();
        char[] formatChars;
        if (longPartChars.length > posPrecision) {
            int end = longPartChars.length - 1;
            int decIndex = longPartChars.length - posPrecision;
            while (end >= decIndex && longPartChars[end] == '0') {
                end--;
            }
            if (end >= decIndex) {
                formatChars = new char[end + 2];
                System.arraycopy(longPartChars, 0, formatChars, 0, decIndex);
                formatChars[decIndex] = '.';
                System.arraycopy(longPartChars, decIndex, formatChars,
                        decIndex + 1, end - decIndex + 1);
            } else {
                formatChars = new char[decIndex];
                System.arraycopy(longPartChars, 0, formatChars, 0, decIndex);
            }
        } else {
            int end = longPartChars.length - 1;
            while (end >= 0 && longPartChars[end] == '0') {
                end--;
            }
            char[] leadings = LEADING_DECIMALS[posPrecision - longPartChars.length];
            formatChars = Arrays.copyOf(leadings, leadings.length + end + 1);
            System.arraycopy(longPartChars, 0, formatChars, leadings.length, end + 1);
        }
        return Math.signum(d) > 0 ? new String(formatChars) : "-" + new String(formatChars);
    }

    private static String bigDecFormat(double d, int precision) {
        String formatStr = new BigDecimal(Double.toString(d)).setScale(Math.abs(precision), RoundingMode.HALF_UP)
                .toString();
        if (precision == 0) {
            return formatStr;
        }
        int end = formatStr.length() - 1;
        while (end >= 0 && formatStr.charAt(end) == '0') {
            end--;
        }
        formatStr = formatStr.substring(0, end + 1);
        if (formatStr.charAt(formatStr.length() - 1) == '.') {
            formatStr = formatStr.substring(0, formatStr.length() - 1);
        }
        return formatStr;
    }

}
