package com.alpaca.library_base.event;

/**
 * @文件名: CateListRefreshEvent
 * @功能描述:
 * @Date : 2018/2/23
 * @email:
 * @修改记录:
 */
public class LikeRefreshEvent {


    public int position;
    public int like;

    public LikeRefreshEvent() {
    }

    public LikeRefreshEvent(int position, int like) {
        this.position = position;
        this.like = like;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
