package com.alpaca.library_base.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/10/30
 */
public class IdolBean extends BaseData implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * browse : 0
         * id : 0
         * imageUrl :
         * number : 0
         * postId : 0
         * residenceTime : 0
         * type : 0
         * windowTime : 0
         */
        @SerializedName("id")
        private int id;
        @SerializedName("sex")
        private int sex;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("name")
        private String name;
        @SerializedName("icon")
        private String image;
        @SerializedName("desc")
        private String intr;

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIntr() {
            return intr;
        }

        public void setIntr(String intr) {
            this.intr = intr;
        }

    }
}
