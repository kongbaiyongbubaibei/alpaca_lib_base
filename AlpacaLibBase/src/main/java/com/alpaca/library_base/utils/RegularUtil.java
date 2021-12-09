package com.alpaca.library_base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {
    /**
     * 提取字符串中的数字
     *
     * @param textStr
     * @return
     */
    public static String cutOutNumber(String textStr) {
        if (textStr == null) return "";
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(textStr);
        return m.replaceAll("").trim();
    }
}
