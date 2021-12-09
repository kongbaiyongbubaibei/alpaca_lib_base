package com.alpaca.library_base.event;

/**
 * @文件名: TeacherVideoLikeEvent
 * @功能描述:
 * @Date : 2021/3/19
 * @email:
 * @修改记录:
 */
public class TeacherVideoLikeEvent {
    int workid;
    int status;
    int likeNum;

    public TeacherVideoLikeEvent(int workid, int status) {
        this.workid = workid;
        this.status = status;
    }

    public TeacherVideoLikeEvent(int workid, int status, int likeNum) {
        this.workid = workid;
        this.status = status;
        this.likeNum = likeNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWorkid() {
        return workid;
    }

    public void setWorkid(int workid) {
        this.workid = workid;
    }
}
