package com.alpaca.library_base.event;

public class RecyclerScrollEvent {
    public int scrollIndex;

    public RecyclerScrollEvent(int scrollIndex) {
        this.scrollIndex = scrollIndex;
    }

    public int getScrollIndex() {
        return scrollIndex;
    }

    public void setScrollIndex(int scrollIndex) {
        this.scrollIndex = scrollIndex;
    }
}
