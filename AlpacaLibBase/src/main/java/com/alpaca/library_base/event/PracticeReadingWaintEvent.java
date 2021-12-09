package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class PracticeReadingWaintEvent {
    public double minute;

    public PracticeReadingWaintEvent() {
    }

    public PracticeReadingWaintEvent(double minute) {
        this.minute = minute;
    }

    public double getMinute() {
        return minute;
    }

    public void setMinute(double minute) {
        this.minute = minute;
    }
}
