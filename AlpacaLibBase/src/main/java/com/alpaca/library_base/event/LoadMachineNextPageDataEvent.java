package com.alpaca.library_base.event;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class LoadMachineNextPageDataEvent {
    public int pageNo;


    public LoadMachineNextPageDataEvent() {
    }

    public LoadMachineNextPageDataEvent(int pageNo) {
        this.pageNo = pageNo;
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
