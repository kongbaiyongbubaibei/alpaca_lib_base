package com.alpaca.library_base.event;

/**
 * @文件名: ClearReadAllEvent
 * @功能描述:
 * @Date : 2018/3/7
 * @email:
 * @修改记录:
 */
public class FavoriteNameChangeEvent {

    public int position;
    public CharSequence charSequence;

    public FavoriteNameChangeEvent() {
    }

    public FavoriteNameChangeEvent(int position, CharSequence charSequence) {
        this.position = position;
        this.charSequence = charSequence;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CharSequence getCharSequence() {
        return charSequence;
    }

    public void setCharSequence(CharSequence charSequence) {
        this.charSequence = charSequence;
    }
}
