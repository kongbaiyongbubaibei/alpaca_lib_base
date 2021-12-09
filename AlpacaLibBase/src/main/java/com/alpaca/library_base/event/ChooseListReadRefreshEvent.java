package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class ChooseListReadRefreshEvent {
    public int readPosition;

    public ChooseListReadRefreshEvent(int readPosition) {
        this.readPosition = readPosition;
    }

    public int getReadPosition() {
        return readPosition;
    }

    public void setReadPosition(int readPosition) {
        this.readPosition = readPosition;
    }
}
