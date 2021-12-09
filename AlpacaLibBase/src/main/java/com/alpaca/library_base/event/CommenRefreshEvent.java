package com.alpaca.library_base.event;

/**
 * @文件名: CateListRefreshEvent
 * @功能描述:
 * @Date : 2018/2/23
 * @email:
 * @修改记录:
 */
public class CommenRefreshEvent {

    public String userName;
    public String content;
    public int position;

    public CommenRefreshEvent() {
    }

    public CommenRefreshEvent(String userName, String content, int position) {
        this.userName = userName;
        this.content = content;
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
