package com.alpaca.library_base.module;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @文件名: AppConfig
 * @功能描述:
 * @Date : 2018/6/22
 * @email:
 * @修改记录:
 */
public class AppConfig {

    /**
     * code : 0
     * msg : success
     * data : {"clock_achievement":{"background":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20190319/761bc187c73741249af9793ef7aad733.png","cover":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20190319/b07eb9eb814049c9aded54aad142f41a.png"},"clock_weixin":{"weixin":"Cassie5526"},"clock_invitation":{"qr_code":"www.baidu.com","background":"www.baidu.com"}}
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
         * clock_achievement : {"background":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20190319/761bc187c73741249af9793ef7aad733.png","cover":"http://oyxe80s4l.bkt.clouddn.com/image/activity/20190319/b07eb9eb814049c9aded54aad142f41a.png"}
         * clock_weixin : {"weixin":"Cassie5526"}
         * clock_invitation : {"qr_code":"www.baidu.com","background":"www.baidu.com"}
         */

        @SerializedName("clock_achievement")
        private ClockAchievementBean clockAchievement;
        @SerializedName("clock_weixin")
        private ClockWeixinBean clockWeixin;
        @SerializedName("user_extend")
        private UserExtendBean userExtend;
        @SerializedName("clock_invitation")
        private ClockInvitationBean clockInvitation;
        private Download download;
        @SerializedName("upload_activity")
        private Download uploadActivity;
        private CourseSeduce courseSeduce;
        private Map<String, AllWindowData> allWindow;
        @SerializedName("courseRefuel")
        private CourseRefuelBean courseRefuel;
        private FiftySound fiftySound;
        @SerializedName("wxSwitch")
        private WxSwitch wxSwitch;

        public Download getUploadActivity() {
            return uploadActivity;
        }

        public void setUploadActivity(Download uploadActivity) {
            this.uploadActivity = uploadActivity;
        }

        public WxSwitch getWxSwitch() {
            return wxSwitch;
        }

        public void setWxSwitch(WxSwitch wxSwitch) {
            this.wxSwitch = wxSwitch;
        }

        public UserExtendBean getUserExtend() {
            return userExtend;
        }

        public void setUserExtend(UserExtendBean userExtend) {
            this.userExtend = userExtend;
        }

        public FiftySound getFiftySound() {
            return fiftySound;
        }

        public void setFiftySound(FiftySound fiftySound) {
            this.fiftySound = fiftySound;
        }

        public static class WxSwitch {
            private String wx;

            public String getWx() {
                return wx;
            }

            public void setWx(String wx) {
                this.wx = wx;
            }
        }

        public static class FiftySound {
            private String translateImg;
            private String guideGoto;
            private String guideImg;
            private String translateGoto;
            private String guideText;
            private String translateText;

            public String getTranslateImg() {
                return translateImg;
            }

            public void setTranslateImg(String translateImg) {
                this.translateImg = translateImg;
            }

            public String getGuideGoto() {
                return guideGoto;
            }

            public void setGuideGoto(String guideGoto) {
                this.guideGoto = guideGoto;
            }

            public String getGuideImg() {
                return guideImg;
            }

            public void setGuideImg(String guideImg) {
                this.guideImg = guideImg;
            }

            public String getTranslateGoto() {
                return translateGoto;
            }

            public void setTranslateGoto(String translateGoto) {
                this.translateGoto = translateGoto;
            }

            public String getGuideText() {
                return guideText;
            }

            public void setGuideText(String guideText) {
                this.guideText = guideText;
            }

            public String getTranslateText() {
                return translateText;
            }

            public void setTranslateText(String translateText) {
                this.translateText = translateText;
            }
        }

        public CourseRefuelBean getCourseRefuel() {
            if (courseRefuel == null) {
                return new CourseRefuelBean();
            }
            return courseRefuel;
        }

        public void setCourseRefuel(CourseRefuelBean courseRefuel) {
            this.courseRefuel = courseRefuel;
        }

        public static class CourseRefuelBean {
            private int appointmentSwitch;
            @SerializedName("switch")
            private int courseSwitch;
            private String advancedCourse;
            private String wx;

            public String getWx() {
                return wx;
            }

            public void setWx(String wx) {
                this.wx = wx;
            }

            public String getAdvancedCourse() {
                return advancedCourse;
            }

            public void setAdvancedCourse(String advancedCourse) {
                this.advancedCourse = advancedCourse;
            }

            public int getCourseSwitch() {
                return courseSwitch;
            }

            public void setCourseSwitch(int courseSwitch) {
                this.courseSwitch = courseSwitch;
            }

            public int getAppointmentSwitch() {
                return appointmentSwitch;
            }

            public void setAppointmentSwitch(int appointmentSwitch) {
                this.appointmentSwitch = appointmentSwitch;
            }
        }

        public static class UserExtendBean {
            @SerializedName("is_get")
            private String isGet;

            public String getIsGet() {
                return isGet;
            }

            public void setIsGet(String isGet) {
                this.isGet = isGet;
            }
        }

        public Map<String, AllWindowData> getAllWindow() {
            return allWindow;
        }

        public void setAllWindow(Map<String, AllWindowData> allWindow) {
            this.allWindow = allWindow;
        }

        public static class AllWindowData {
            private int id;
            private String winMsg;
            private String winWeixin;
            private int typeEnum;
            private int clientType;
            private String miniprogramPath;

            public String getMiniprogramPath() {
                return miniprogramPath;
            }

            public void setMiniprogramPath(String miniprogramPath) {
                this.miniprogramPath = miniprogramPath;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getWinMsg() {
                return winMsg;
            }

            public void setWinMsg(String winMsg) {
                this.winMsg = winMsg;
            }

            public String getWinWeixin() {
                return winWeixin;
            }

            public void setWinWeixin(String winWeixin) {
                this.winWeixin = winWeixin;
            }

            public int getTypeEnum() {
                return typeEnum;
            }

            public void setTypeEnum(int typeEnum) {
                this.typeEnum = typeEnum;
            }

            public int getClientType() {
                return clientType;
            }

            public void setClientType(int clientType) {
                this.clientType = clientType;
            }
        }

        public CourseSeduce getCourseSeduce() {
            return courseSeduce;
        }

        public void setCourseSeduce(CourseSeduce courseSeduce) {
            this.courseSeduce = courseSeduce;
        }

        public Download getDownload() {
            return download;
        }

        public void setDownload(Download download) {
            this.download = download;
        }

        public static class CourseSeduce {
            private String wxCode;
            private String windowText;

            public String getWxCode() {
                return wxCode;
            }

            public void setWxCode(String wxCode) {
                this.wxCode = wxCode;
            }

            public String getWindowText() {
                return windowText;
            }

            public void setWindowText(String windowText) {
                this.windowText = windowText;
            }
        }

        public static class Download {
            private String url;
            private String testOverIconUrl;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTestOverIconUrl() {
                return testOverIconUrl;
            }

            public void setTestOverIconUrl(String testOverIconUrl) {
                this.testOverIconUrl = testOverIconUrl;
            }
        }

        public ClockAchievementBean getClockAchievement() {
            return clockAchievement;
        }

        public void setClockAchievement(ClockAchievementBean clockAchievement) {
            this.clockAchievement = clockAchievement;
        }

        public ClockWeixinBean getClockWeixin() {
            return clockWeixin;
        }

        public void setClockWeixin(ClockWeixinBean clockWeixin) {
            this.clockWeixin = clockWeixin;
        }

        public ClockInvitationBean getClockInvitation() {
            return clockInvitation;
        }

        public void setClockInvitation(ClockInvitationBean clockInvitation) {
            this.clockInvitation = clockInvitation;
        }

        public static class ClockAchievementBean {
            /**
             * background : http://oyxe80s4l.bkt.clouddn.com/image/activity/20190319/761bc187c73741249af9793ef7aad733.png
             * cover : http://oyxe80s4l.bkt.clouddn.com/image/activity/20190319/b07eb9eb814049c9aded54aad142f41a.png
             */

            @SerializedName("background")
            private String background;
            @SerializedName("cover")
            private String cover;

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }

        public static class ClockWeixinBean {
            /**
             * weixin : Cassie5526
             */

            @SerializedName("wx_code")
            private String weixin;
            @SerializedName("officialAccount")
            private String officialAccount;

            public String getOfficialAccount() {
                return officialAccount;
            }

            public void setOfficialAccount(String officialAccount) {
                this.officialAccount = officialAccount;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }
        }

        public static class ClockInvitationBean {
            /**
             * qr_code : www.baidu.com
             * background : www.baidu.com
             */

            @SerializedName("qr_code")
            private String qrCode;
            @SerializedName("background")
            private String background;

            public String getQrCode() {
                return qrCode;
            }

            public void setQrCode(String qrCode) {
                this.qrCode = qrCode;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }
        }
    }
}
