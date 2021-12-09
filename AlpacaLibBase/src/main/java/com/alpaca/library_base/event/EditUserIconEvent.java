package com.alpaca.library_base.event;

/**
 * @文件名: EditUserSexEvent
 * @功能描述:
 * @Date : 2019/8/14
 * @email:
 * @修改记录:
 */
public class EditUserIconEvent {
    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public EditUserIconEvent(String path) {
        this.path = path;
    }
}
