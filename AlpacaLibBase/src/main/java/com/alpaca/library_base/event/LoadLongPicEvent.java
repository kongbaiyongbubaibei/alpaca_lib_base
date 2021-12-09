package com.alpaca.library_base.event;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * 更新我的课程倒计时间
 */
public class LoadLongPicEvent {
    private ArrayList<Bitmap> bitmapList;

    public LoadLongPicEvent(ArrayList<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    public ArrayList<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(ArrayList<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }
}
