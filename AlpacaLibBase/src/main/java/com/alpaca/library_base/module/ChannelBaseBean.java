package com.alpaca.library_base.module;

/**
 * @文件名: ChannelBaseBean
 * @功能描述:
 * @Date : 2020/12/28
 * @email:
 * @修改记录:
 */
public class ChannelBaseBean extends BaseData {
    private ChannelBean data;

    public ChannelBaseBean(ChannelBean data) {
        this.data = data;
    }

    public ChannelBean getData() {
        return data;
    }

    public void setData(ChannelBean data) {
        this.data = data;
    }
}
