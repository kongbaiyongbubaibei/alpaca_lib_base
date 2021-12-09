package com.alpaca.library_base.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @文件名: UserDetailData
 * @功能描述:
 * @Date : 2018/2/23
 * @email:
 * @修改记录:
 */
public class UserDetailBean implements Serializable {

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

    public static class DataBean implements Serializable {

        @SerializedName("uid")
        private String uid = "";
        @SerializedName("userName")
        private String userName;
        @SerializedName("icon")
        private String icon;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("openId")
        private String openId;
        @SerializedName("sex")
        private int sex;
        @SerializedName("id")
        private int id;
        @SerializedName("energy")
        private int energy;
        @SerializedName("status")
        private int status;
        @SerializedName("language")
        private String language;
        @SerializedName("endTime")
        private long endTime;
        @SerializedName("city")
        private String city;
        @SerializedName("province")
        private String province;
        @SerializedName("country")
        private String country;
        @SerializedName("createTime")
        private long createTime;
        @SerializedName("tuoAge")
        private int tuoAge;
        @SerializedName("isMembers")
        private int isMembers;
        @SerializedName("isOldMembers")
        private int isOldMembers;
        @SerializedName("likeNum")
        private int likeNum;
        @SerializedName("collectNum")
        private int collectNum;
        @SerializedName("pushMsgCnt")
        private int pushMsgCnt;
        @SerializedName("worksCnt")
        private int worksCnt;
        //0影视 1摄影
        @SerializedName("userType")
        private int userType;

        @SerializedName("isFill")
        private boolean isFill;

        @SerializedName("isCourseRefuel")
        private int isCourseRefuel = -1;//课程加油站是否预约过课程
        private boolean isCollectInformation;

        public int getIsMembers() {
            return isMembers;
        }

        public void setIsMembers(int isMembers) {
            this.isMembers = isMembers;
        }

        public int getIsOldMembers() {
            return isOldMembers;
        }

        public void setIsOldMembers(int isOldMembers) {
            this.isOldMembers = isOldMembers;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getPushMsgCnt() {
            return pushMsgCnt;
        }

        public void setPushMsgCnt(int pushMsgCnt) {
            this.pushMsgCnt = pushMsgCnt;
        }

        public int getWorksCnt() {
            return worksCnt;
        }

        public void setWorksCnt(int worksCnt) {
            this.worksCnt = worksCnt;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public boolean isCollectInformation() {
            return isCollectInformation;
        }

        public void setCollectInformation(boolean collectInformation) {
            isCollectInformation = collectInformation;
        }

        public int getIsCourseRefuel() {
            return isCourseRefuel;
        }

        public void setIsCourseRefuel(int isCourseRefuel) {
            this.isCourseRefuel = isCourseRefuel;
        }

        public boolean isFill() {
            return isFill;
        }

        public void setFill(boolean fill) {
            isFill = fill;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public int getEnergy() {
            return energy;
        }

        public void setEnergy(int energy) {
            this.energy = energy;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }


        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getTuoAge() {
            return tuoAge;
        }

        public void setTuoAge(int tuoAge) {
            this.tuoAge = tuoAge;
        }
    }
}
