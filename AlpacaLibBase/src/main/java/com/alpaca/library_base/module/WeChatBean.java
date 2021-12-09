package com.alpaca.library_base.module;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @文件名: WeChatBean
 * @功能描述:
 * @Date : 2020/11/26
 * @email:
 * @修改记录:
 */
public class WeChatBean {

    /**
     * code : 0
     * msg : success
     * data : {"communityStudy":{"id":20,"wxCode":"ytys1110","desc":"社区学习群"},"brush_vx":{"id":96,"wxCode":"底部文案","desc":"笔刷vx","miniprogramPath":"","jumpUrl":"https://mp.weixin.qq.com/s/qHYn8wWiGZae8r00EPZkmw"},"about_android":{"id":94,"wxCode":"https://res.ytaxx.com/image/activity/20201126/8128170e1a78467b8fb9df414eedb4e6.png"},"teacherCommentsWx":{"id":14,"wxCode":"ytys1110","desc":"口语真经老师点评"},"clockComment":{"id":22,"wxCode":"ytys1110","desc":"打卡点评群"},"oralClassicsWx":{"id":52,"wxCode":"ytys1110","desc":"打卡：口语真经打卡加入战团"},"courseEnrolmentWx":{"id":13,"wxCode":"ytys1110","desc":"课程推荐报名"},"consultantWx":{"id":12,"wxCode":"ytys1110","desc":"咨询顾问"},"shadowReadingWx":{"id":10,"wxCode":"ytys1110","desc":"打卡：影子跟读打卡加入战团"},"liveBroadcast":{"id":21,"wxCode":"ytys1110","desc":"直播课报名"},"vipWx":{"id":80,"wxCode":"ytys1110","desc":"会员"},"feedbackWx":{"id":19,"wxCode":"ytys1110","desc":"意见反馈"},"spokenLanguageFrameworkWx":{"id":9,"wxCode":"ytys1110","desc":"打卡：口语框架打卡加入战团"},"about_ios":{"id":93,"wxCode":"https://res.ytaxx.com/image/activity/20201126/8128170e1a78467b8fb9df414eedb4e6.png","desc":""},"referenceGuideWx":{"id":18,"wxCode":"ytys1110","desc":"备考指南H5"},"dataCircleWx":{"id":16,"wxCode":"ytys1110","desc":"资料圈"},"classWx":{"id":92,"wxCode":"","miniprogramPath":"package/pages/GuideLink/GuideLink?type=2","jumpUrl":""},"oralClassicsTeacherWx":{"id":15,"wxCode":"ytys1110","desc":"口语真经添加老师微信"},"referencePlan":{"id":17,"wxCode":"ytys1110","desc":"备考计划"}}
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
        private Map<String, AllWindowData> allWindow;

        public Map<String, AllWindowData> getAllWindow() {
            return allWindow;
        }

        public void setAllWindow(Map<String, AllWindowData> allWindow) {
            this.allWindow = allWindow;
        }

        public static class AllWindowData {
            private int id;
            private String desc;
            private String wxCode;
            private String miniprogramPath;
            private String jumpUrl;

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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getWxCode() {
                return wxCode;
            }

            public void setWxCode(String wxCode) {
                this.wxCode = wxCode;
            }

            public String getJumpUrl() {
                return jumpUrl;
            }

            public void setJumpUrl(String jumpUrl) {
                this.jumpUrl = jumpUrl;
            }
        }
    }
}
