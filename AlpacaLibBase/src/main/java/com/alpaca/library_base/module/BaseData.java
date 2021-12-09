package com.alpaca.library_base.module;

import com.google.gson.annotations.SerializedName;

/**
 * @文件名: BaseData
 * @功能描述:
 * @Date : 2018/3/1
 * @email:
 * @修改记录:
 */
public class BaseData {

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
