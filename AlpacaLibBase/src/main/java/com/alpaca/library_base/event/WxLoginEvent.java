package com.alpaca.library_base.event;

/**
 * @文件名: WxLoginEvent
 * @功能描述:
 * @Date : 2018/2/28
 * @email:
 * @修改记录:
 */
public class WxLoginEvent {

    public boolean loginSuc;
    public String loginCode;

    public WxLoginEvent(boolean loginSuc, String loginCode) {
        this.loginSuc = loginSuc;
        this.loginCode = loginCode;
    }

}
