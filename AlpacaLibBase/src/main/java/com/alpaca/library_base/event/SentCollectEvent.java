package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class SentCollectEvent {
    public String tag;
    public int currentPostion;
    public int  isCollection;

    public SentCollectEvent(String tag, int currentPostion, int isCollection) {
        this.tag = tag;
        this.currentPostion = currentPostion;
        this.isCollection = isCollection;
    }
}
