package com.alpaca.library_base.event;

/**
 * @文件名: ReadRefreshEvent
 * @功能描述:
 * @Date : 2018/3/7
 * @email:
 * @修改记录:
 */
public class ReadRefreshEvent {

    public long articleId;
    public int tag;
    public int type;
    public int status;

    public ReadRefreshEvent(long articleId, int tag, int type, int status) {
        this.articleId = articleId;
        this.tag = tag;
        this.type = type;
        this.status = status;
    }
}
