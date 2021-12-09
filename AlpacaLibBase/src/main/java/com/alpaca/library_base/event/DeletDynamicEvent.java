package com.alpaca.library_base.event;

/**
 * @文件名: CateListRefreshEvent
 * @功能描述:
 * @Date : 2018/2/23
 * @email:
 * @修改记录:
 */
public class DeletDynamicEvent {


    public int position;

    public DeletDynamicEvent() {
    }

    public DeletDynamicEvent(int position) {
        this.position = position;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
