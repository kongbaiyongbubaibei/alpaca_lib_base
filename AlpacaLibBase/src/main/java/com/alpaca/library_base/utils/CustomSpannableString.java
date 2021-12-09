package com.alpaca.library_base.utils;

import android.text.NoCopySpan;
import android.text.SpannableString;

/**
 * class desc :
 *
 * @author : zzh
 * date : 2020/3/30
 */
public class CustomSpannableString extends SpannableString implements CustomSpannable{

    /**
     * For the backward compatibility reasons, this constructor copies all spans including {@link
     * NoCopySpan}.
     *
     * @param source source text
     */
    public CustomSpannableString(CharSequence source) {
        super(source);
    }

    @Override
    public void setSpan(Object what, int start, int end, int flags) {
        if (start < 0 || end < 0 || start == end) {
            return;
        }
        super.setSpan(what, start, end, flags);
    }

}
