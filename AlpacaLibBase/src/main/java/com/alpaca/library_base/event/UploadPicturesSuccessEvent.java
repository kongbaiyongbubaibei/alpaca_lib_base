package com.alpaca.library_base.event;

import java.util.ArrayList;

/**
 * @文件名: UploadPicturesSuccessEvent
 * @功能描述:
 * @Date : 2020/11/16
 * @email:
 * @修改记录:
 */
public class UploadPicturesSuccessEvent {

    ArrayList<String> resultImagePath = new ArrayList<>();

    public UploadPicturesSuccessEvent(ArrayList<String> resultImagePath) {
        this.resultImagePath = resultImagePath;
    }

    public ArrayList<String> getResultImagePath() {
        return resultImagePath;
    }

    public void setResultImagePath(ArrayList<String> resultImagePath) {
        this.resultImagePath = resultImagePath;
    }
}
