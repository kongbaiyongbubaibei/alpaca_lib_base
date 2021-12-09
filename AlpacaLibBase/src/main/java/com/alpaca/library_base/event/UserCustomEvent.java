package com.alpaca.library_base.event;

import android.text.TextUtils;

/**
 * @文件名: UserCustomEvent
 * @功能描述:
 * @Date : 2021/3/10
 * @email:
 * @修改记录:
 */
public class UserCustomEvent {
    private String operation = "";
    private String extendString = "";

    public UserCustomEvent(String operation) {
        this.operation = operation;
    }

    public UserCustomEvent(String operation, String extendString) {
        this.operation = operation;
        if (!TextUtils.isEmpty(extendString)) {
            this.extendString = extendString;
        }
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getExtendString() {
        return extendString;
    }

    public void setExtendString(String extendString) {
        this.extendString = extendString;
    }
}
