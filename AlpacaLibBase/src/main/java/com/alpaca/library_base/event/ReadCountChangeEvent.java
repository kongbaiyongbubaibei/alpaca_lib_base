package com.alpaca.library_base.event;

/**
 * @文件名: ReadCountChangeEvent
 * @功能描述:
 * @Date : 2018/3/7
 * @email:
 * @修改记录:
 */
public class ReadCountChangeEvent {

    private boolean isAdd;

    public ReadCountChangeEvent(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
