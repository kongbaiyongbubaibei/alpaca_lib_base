package com.alpaca.library_base.event;

/**
 * 分享成功的消息
 */
public class ShareSuccEvent {

    public static final int SHARED_TYPE_WX = 1;//微信
    public static final int SHARED_TYPE_QQ = 2;//QQ

    private int sharedType;

    public ShareSuccEvent(int sharedType) {
        this.sharedType = sharedType;
    }

    public int getSharedType() {
        return sharedType;
    }
}
