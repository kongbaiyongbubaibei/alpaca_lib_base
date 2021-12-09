package com.alpaca.library_base.utils;

import android.text.SpannableStringBuilder;

/**
 * class desc :
 *
 * @author : zzh
 * date : 2020/3/30
 */
public class CustomSpannableStringBuilder extends SpannableStringBuilder {

    public CustomSpannableStringBuilder() {
        super();
    }

    public CustomSpannableStringBuilder(CharSequence source) {
        super(source);
    }

    public CustomSpannableStringBuilder(CharSequence text, int start, int end) {
        super(text, start, end);
    }

    @Override
    public void setSpan(Object what, int start, int end, int flags) {
        if (start < 0 || end < 0 || start == end) {
            return;
        }
        super.setSpan(what, start, end, flags);
    }

    public static CustomSpannableStringBuilder valueOf(CharSequence source) {
        if (source instanceof CustomSpannableStringBuilder) {
            return (CustomSpannableStringBuilder) source;
        } else {
            return new CustomSpannableStringBuilder(source);
        }
    }

    @Override
    public CustomSpannableStringBuilder append(char text) {
        return (CustomSpannableStringBuilder) super.append(text);
    }

    @Override
    public CustomSpannableStringBuilder append(CharSequence text) {
        return (CustomSpannableStringBuilder) super.append(text);
    }

    @Override
    public CustomSpannableStringBuilder append(CharSequence text, int start, int end) {
        return (CustomSpannableStringBuilder) super.append(text, start, end);
    }

    @Override
    public CustomSpannableStringBuilder append(CharSequence text, Object what, int flags) {
        return (CustomSpannableStringBuilder) super.append(text, what, flags);
    }
}
