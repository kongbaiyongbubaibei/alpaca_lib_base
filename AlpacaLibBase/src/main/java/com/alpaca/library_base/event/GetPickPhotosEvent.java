package com.alpaca.library_base.event;

import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @文件名: GetPickPhotosEvent
 * @功能描述:
 * @Date : 2020/11/25
 * @email:
 * @修改记录:
 */
public class GetPickPhotosEvent {
    private List<ImageItem> iData = new ArrayList<>();

    public GetPickPhotosEvent(List<ImageItem> iData) {
        this.iData = iData;
    }

    public List<ImageItem> getiData() {
        return iData;
    }

    public void setiData(List<ImageItem> iData) {
        this.iData = iData;
    }
}
