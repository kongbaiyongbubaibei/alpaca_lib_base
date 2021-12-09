package com.alpaca.library_base.event;

/**
 * @文件名: ContractsPicturesEvent
 * @功能描述:
 * @Date : 2021/2/2
 * @email:
 * @修改记录:
 */
public class ContractsBottomEvent {
    String bottomPath;

    public ContractsBottomEvent(String bottomPath) {
        this.bottomPath = bottomPath;
    }


    public String getBottomPath() {
        return bottomPath;
    }

    public void setBottomPath(String bottomPath) {
        this.bottomPath = bottomPath;
    }
}
