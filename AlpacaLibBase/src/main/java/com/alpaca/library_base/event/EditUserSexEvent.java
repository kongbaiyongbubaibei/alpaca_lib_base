package com.alpaca.library_base.event;

/**
 * @文件名: EditUserSexEvent
 * @功能描述:
 * @Date : 2019/8/14
 * @email:
 * @修改记录:
 */
public class EditUserSexEvent {
    int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public EditUserSexEvent(int sex) {
        this.sex = sex;
    }
}
