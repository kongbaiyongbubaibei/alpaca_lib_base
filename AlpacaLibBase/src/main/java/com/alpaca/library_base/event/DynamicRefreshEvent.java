package com.alpaca.library_base.event;

/**
 * @文件名: CateListRefreshEvent
 * @功能描述:
 * @Date : 2018/2/23
 * @email:
 * @修改记录:
 */
public class DynamicRefreshEvent {

    public int cardId;
    private String color;
    public DynamicRefreshEvent(int cardId) {
        this.cardId = cardId;
    }

    public DynamicRefreshEvent(int cardId, String color) {
        this.cardId = cardId;
        this.color = color;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
