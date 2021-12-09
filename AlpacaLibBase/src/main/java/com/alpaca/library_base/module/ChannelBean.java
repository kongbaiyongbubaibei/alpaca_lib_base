package com.alpaca.library_base.module;

/**
 * @文件名: ChannelBean
 * @功能描述:
 * @Date : 2020/12/28
 * @email:
 * @修改记录:
 */
public class ChannelBean {
    private String channelId;
    private String sellId;

    public ChannelBean(String channelId, String sellId) {
        this.channelId = channelId;
        this.sellId = sellId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getSellId() {
        return sellId;
    }

    public void setSellId(String sellId) {
        this.sellId = sellId;
    }
}
