package com.alpaca.library_base.event;

public class PushMessageEvent {
    public int redPoint;

    public PushMessageEvent() {
    }

    public PushMessageEvent(int redPoint) {
        this.redPoint = redPoint;
    }

    public int getRedPoint() {
        return redPoint;
    }

    public void setRedPoint(int redPoint) {
        this.redPoint = redPoint;
    }
}
