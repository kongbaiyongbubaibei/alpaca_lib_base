package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class CutToMessageEvent {

   public String content;
    public int parentId;

    public CutToMessageEvent() {
    }

    public CutToMessageEvent(String content, int parentId) {
        this.content = content;
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }


}
