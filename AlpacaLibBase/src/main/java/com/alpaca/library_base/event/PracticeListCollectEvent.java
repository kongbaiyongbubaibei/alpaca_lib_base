package com.alpaca.library_base.event;

/**
 * @文件名: ClearReadAllEvent
 * @功能描述:
 * @Date : 2018/3/7
 * @email:
 * @修改记录:
 */
public class PracticeListCollectEvent {
    private int collect;

    public PracticeListCollectEvent() {
    }

    public PracticeListCollectEvent(int collect) {
        this.collect = collect;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }


}
