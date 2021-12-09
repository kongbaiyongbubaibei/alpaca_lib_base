package com.alpaca.library_base.event;

/**
 * @文件名: ClearReadAllEvent
 * @功能描述:
 * @Date : 2018/3/7
 * @email:
 * @修改记录:
 */
public class TexViewLoadHtmlEvent {
   public CharSequence charSequence;

    public TexViewLoadHtmlEvent() {
    }

    public TexViewLoadHtmlEvent(CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    public CharSequence getCharSequence() {
        return charSequence;
    }

    public void setCharSequence(CharSequence charSequence) {
        this.charSequence = charSequence;
    }
}
