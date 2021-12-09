package com.alpaca.library_base.event;

import java.util.List;

/**
 * @文件名: BrowseRefreshEvent
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class GetIdListEvent {
    public List<Long> idsList;
    public GetIdListEvent(List<Long> idsList) {
        this.idsList = idsList;
    }

    public List<Long> getIdsList() {
        return idsList;
    }

    public void setIdsList(List<Long> idsList) {
        this.idsList = idsList;
    }
}
