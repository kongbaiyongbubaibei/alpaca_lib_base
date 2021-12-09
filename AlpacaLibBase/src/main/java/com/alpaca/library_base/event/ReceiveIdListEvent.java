package com.alpaca.library_base.event;

import java.util.List;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class ReceiveIdListEvent {
    public List<Integer> idsList;
    public ReceiveIdListEvent(List<Integer> idsList) {
        this.idsList = idsList;
    }


    public List<Integer> getIdsList() {
        return idsList;
    }

    public void setIdsList(List<Integer> idsList) {
        this.idsList = idsList;
    }
}
