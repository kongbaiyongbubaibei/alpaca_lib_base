package com.alpaca.library_base.event;

/**
 * @文件名: CateListRefreshEvent
 * @功能描述:
 * @Date : 2018/2/23
 * @email:
 * @修改记录:
 */
public class DeleteCommenEvent {

    public int comentPosition;
    public int dynamicPosition;

    public DeleteCommenEvent() {
    }

    public DeleteCommenEvent(int comentPosition, int dynamicPosition) {
        this.comentPosition = comentPosition;
        this.dynamicPosition = dynamicPosition;
    }

    public int getComentPosition() {
        return comentPosition;
    }

    public void setComentPosition(int comentPosition) {
        this.comentPosition = comentPosition;
    }

    public int getDynamicPosition() {
        return dynamicPosition;
    }

    public void setDynamicPosition(int dynamicPosition) {
        this.dynamicPosition = dynamicPosition;
    }
}
