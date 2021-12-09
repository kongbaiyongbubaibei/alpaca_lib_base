package com.alpaca.library_base.event;

/**
 * @文件名: ContractsPicturesEvent
 * @功能描述:
 * @Date : 2021/2/2
 * @email:
 * @修改记录:
 */
public class ContractsPicturesEvent {
    String topPath;
    String bottomPath;

    public ContractsPicturesEvent(String topPath, String bottomPath) {
        this.topPath = topPath;
        this.bottomPath = bottomPath;
    }

    public String getTopPath() {
        return topPath;
    }

    public void setTopPath(String topPath) {
        this.topPath = topPath;
    }

    public String getBottomPath() {
        return bottomPath;
    }

    public void setBottomPath(String bottomPath) {
        this.bottomPath = bottomPath;
    }
}
