package com.alpaca.library_base.event;

public class DiListRereshEvent {
    private String imgTypeName;


    public DiListRereshEvent(String imgTypeName) {
        this.imgTypeName = imgTypeName;

    }


    public String getImgTypeName() {
        return imgTypeName;
    }

    public void setImgTypeName(String imgTypeName) {
        this.imgTypeName = imgTypeName;
    }
}
