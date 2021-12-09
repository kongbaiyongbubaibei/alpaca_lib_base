package com.alpaca.library_base.event;

/**
 * @文件名: UploadSinglePicturesSuccessEvent
 * @功能描述:
 * @Date : 2021/3/22
 * @email:
 * @修改记录:
 */
public class UploadSinglePicturesSuccessEvent {
    private String imagePath;

    public UploadSinglePicturesSuccessEvent(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
