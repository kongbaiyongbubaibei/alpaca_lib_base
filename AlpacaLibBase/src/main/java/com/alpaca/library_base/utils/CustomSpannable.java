package com.alpaca.library_base.utils;

import android.text.SpannableString;
import android.text.Spanned;

/**
 * @文件名: CustomSpannable
 * @功能描述:
 * @Date : 2020/7/30
 * @email:
 * @修改记录:
 */
public interface CustomSpannable
        extends Spanned
{
    /**
     * Attach the specified markup object to the range <code>start&hellip;end</code>
     * of the text, or move the object to that range if it was already
     * attached elsewhere.  See {@link Spanned} for an explanation of
     * what the flags mean.  The object can be one that has meaning only
     * within your application, or it can be one that the text system will
     * use to affect text display or behavior.  Some noteworthy ones are
     * the subclasses of {@link android.text.style.CharacterStyle} and
     * {@link android.text.style.ParagraphStyle}, and
     * {@link android.text.TextWatcher} and
     * {@link android.text.SpanWatcher}.
     */
    public void setSpan(Object what, int start, int end, int flags);

    /**
     * Remove the specified object from the range of text to which it
     * was attached, if any.  It is OK to remove an object that was never
     * attached in the first place.
     */
    public void removeSpan(Object what);

    /**
     * Remove the specified object from the range of text to which it
     * was attached, if any.  It is OK to remove an object that was never
     * attached in the first place.
     *
     * See {@link Spanned} for an explanation of what the flags mean.
     *
     * @hide
     */
    default void removeSpan(Object what, int flags) {
        removeSpan(what);
    }

    /**
     * Factory used by TextView to create new {@link android.text.Spannable Spannables}. You can subclass
     * it to provide something other than {@link SpannableString}.
     *
     * @see android.widget.TextView#setSpannableFactory(android.text.Spannable.Factory)
     */
    public static class Factory {
        private static Factory sInstance = new Factory();

        /**
         * Returns the standard Spannable Factory.
         */
        public static Factory getInstance() {
            return sInstance;
        }

        /**
         * Returns a new SpannableString from the specified CharSequence.
         * You can override this to provide a different kind of Spannable.
         */
        public CustomSpannable newSpannable(CharSequence source) {
            return new CustomSpannableString(source);
        }
    }
}
