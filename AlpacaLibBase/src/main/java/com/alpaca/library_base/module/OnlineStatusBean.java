package com.alpaca.library_base.module;

import com.google.gson.annotations.SerializedName;

/**
 * @文件名: OnlineStatusBean
 * @功能描述:
 * @Date : 2020/5/21
 * @email:
 * @修改记录:
 */
public class OnlineStatusBean {

    /**
     * code : 0
     * msg : success
     * data : {"id":1,"title":"PTE萌新怎么拿65","introOne":"更多学习干货，直播开讲","jumpLink":"https://tapi.ytaxx.com/api/swagger-ui.html","startTime":1589872923000,"endTime":1590909727000,"isAppointment":false,"appointment":316}
     */

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("data")
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : PTE萌新怎么拿65
         * introOne : 更多学习干货，直播开讲
         * jumpLink : https://tapi.ytaxx.com/api/swagger-ui.html
         * startTime : 1589872923000
         * endTime : 1590909727000
         * isAppointment : false
         * appointment : 316
         */

        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("introOne")
        private String introOne;
        @SerializedName("jumpLink")
        private String jumpLink;
        @SerializedName("startTime")
        private long startTime;
        @SerializedName("endTime")
        private long endTime;
        @SerializedName("isAppointment")
        private boolean isAppointment;
        @SerializedName("appointment")
        private int appointment;
        //0:还未开始
        //1:正在直播
        //2:直播结束
        private int onlineStatus;

        public boolean isAppointment() {
            return isAppointment;
        }

        public void setAppointment(boolean appointment) {
            isAppointment = appointment;
        }

        public int getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(int onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIntroOne() {
            return introOne;
        }

        public void setIntroOne(String introOne) {
            this.introOne = introOne;
        }

        public String getJumpLink() {
            return jumpLink;
        }

        public void setJumpLink(String jumpLink) {
            this.jumpLink = jumpLink;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public boolean isIsAppointment() {
            return isAppointment;
        }

        public void setIsAppointment(boolean isAppointment) {
            this.isAppointment = isAppointment;
        }

        public int getAppointment() {
            return appointment;
        }

        public void setAppointment(int appointment) {
            this.appointment = appointment;
        }
    }
}
