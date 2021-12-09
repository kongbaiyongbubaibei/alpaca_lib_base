package com.alpaca.library_base.utils;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;

import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.utils.netstatus.PixelUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.ColorRes;


public class SpannableUtil {

    public static CustomSpannableStringBuilder changeTextColor(int color, String contentStr, String keyStr) {
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        CustomSpannableStringBuilder mBuilder = new CustomSpannableStringBuilder(contentStr);
        int start = contentStr.toLowerCase().indexOf(keyStr.toLowerCase());
        if (start >= 0 && keyStr.length() > 0) {
            mBuilder.setSpan(span, start, start + keyStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return mBuilder;
    }

    public static CustomSpannableStringBuilder changeTextColor(String contentStr, String keyStr, Object... spans) {
        CustomSpannableStringBuilder mBuilder = new CustomSpannableStringBuilder(contentStr);
        int start = contentStr.toLowerCase().indexOf(keyStr.toLowerCase());
        if (start >= 0 && keyStr.length() > 0) {
            for (Object span : spans) {
                mBuilder.setSpan(span, start, start + keyStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return mBuilder;
    }

    public static CustomSpannableStringBuilder changeTextColorUnderline(Object span, String contentStr, String keyStr) {
        CustomSpannableStringBuilder mBuilder = new CustomSpannableStringBuilder(contentStr);
        int start = contentStr.toLowerCase().indexOf(keyStr.toLowerCase());
        if (start >= 0) {
            mBuilder.setSpan(span, start, start + keyStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mBuilder.setSpan(new UnderlineSpan(), start, start + keyStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return mBuilder;
    }

    public static CustomSpannableStringBuilder changeTextSize(int textSize, int start, String contentStr, String keyStr) {
        CustomSpannableStringBuilder stringBuilder = new CustomSpannableStringBuilder();
        stringBuilder.append(contentStr);
        stringBuilder.setSpan(new AbsoluteSizeSpan(PixelUtil.sp2px(textSize)), start, keyStr.length(), SpannableString
                .SPAN_EXCLUSIVE_INCLUSIVE);
        return stringBuilder;
    }


    public static SpannableString textColor(String content, @ColorRes int color) {
        SpannableString spannableStr = new SpannableString(content);
        if (spannableStr.length() > 0) {
            spannableStr.setSpan(new ForegroundColorSpan(BaseApplication.getInstance().getResources().getColor(color)), 0, spannableStr
                    .length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannableStr;
    }

    /**
     * 多个关键字高亮变色
     *
     * @param color   变化的色值
     * @param s       文字
     * @param keyword 文字中的关键字数组
     */
    public static SpannableString changeTextColor(int color, SpannableString s, String... keyword) {
        for (int i = 0; i < keyword.length; i++) {
            Pattern p = Pattern.compile(keyword[i], Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                if (start > 0 && end > 0 && start < end) {
                    s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return s;
    }

    /**
     * 字体加粗
     *
     * @param content  整个内容
     * @param keywords 需要加粗字
     */
    public static SpannableString boldText(SpannableString content, String... keywords) {
        for (String keyword : keywords) {
            int start = content.toString().indexOf(keyword);
            if (start >= 0 && keyword.length() > 0) {
                content.setSpan(new BoldStyleSpan(Typeface.NORMAL), start, start + keyword.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return content;
    }

    public static class BoldStyleSpan extends StyleSpan {
        public BoldStyleSpan(int style) {
            super(style);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setFakeBoldText(true);
            super.updateDrawState(ds);
        }

        @Override
        public void updateMeasureState(TextPaint paint) {
            paint.setFakeBoldText(true);
            super.updateMeasureState(paint);
        }
    }
}
