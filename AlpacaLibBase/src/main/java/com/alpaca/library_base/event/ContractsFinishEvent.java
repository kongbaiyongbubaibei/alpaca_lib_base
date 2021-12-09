package com.alpaca.library_base.event;

/**
 * @文件名: ContractsPicturesEvent
 * @功能描述:
 * @Date : 2021/2/2
 * @email:
 * @修改记录:
 */
public class ContractsFinishEvent {
    String finishPath;

    public ContractsFinishEvent(String finishPath) {
        this.finishPath = finishPath;
    }

    public String getFinishPath() {
        return finishPath;
    }

    public void setFinishPath(String finishPath) {
        this.finishPath = finishPath;
    }
}
