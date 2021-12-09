package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class VoiceReogResultEvent {
    private String chivoxResult;
    private String baiDuResult;
    private boolean onEnginError = false;

    public boolean isOnEnginError() {
        return onEnginError;
    }

    public void setOnEnginError(boolean onEnginError) {
        this.onEnginError = onEnginError;
    }

    public String getChivoxResult() {
        return chivoxResult;
    }

    public void setChivoxResult(String chivoxResult) {
        this.chivoxResult = chivoxResult;
    }

    public String getBaiDuResult() {
        return baiDuResult;
    }

    public void setBaiDuResult(String baiDuResult) {
        this.baiDuResult = baiDuResult;
    }
}
