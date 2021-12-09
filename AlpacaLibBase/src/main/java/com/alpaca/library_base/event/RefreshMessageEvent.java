package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class RefreshMessageEvent {

    public String content;
    public long parentId;
    public int syncStatus;

    public RefreshMessageEvent() {
    }

    public RefreshMessageEvent(String content, long parentId, int syncStatus) {
        this.content = content;
        this.parentId = parentId;
        this.syncStatus = syncStatus;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }


}
