package com.alpaca.library_base.constants;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.module.IdolBean;
import com.alpaca.library_base.module.OnlineStatusBean;
import com.alpaca.library_base.module.lexicon.LexiconInfoBean;
import com.alpaca.library_base.net.HttpUrl;
import com.alpaca.library_base.utils.DensityUtil;

import java.io.File;
import java.util.Random;

import static com.alpaca.library_base.utils.AppFilePath.getExternalFilesDir;

public class Constants {


    //微信回流
    public static String WX_CODE_TURNS = "yangtuo02";//参与轮换的微信号
    public static String WX_MINIPROGRAM_PATH = "";//参与轮换的微信小程序地址
    public static String WX_SEVEN_MINIPROGRAM_PATH = "";//7天参与轮换的微信小程序地址
    public static String WX_FILE_MINIPROGRAM_PATH = "";//文件参与轮换的微信小程序地址
    public static final String HOME_FESTIVAL_GIF = "https://res.ytaxx.com/image/activity/20200929/2934ade0fe9447bb92dba00e48e8ac67.gif";


    //隐私用户协议
    public static final String PRIVACY_URL = "https://premiere.yangtuoedu.com/appPage/privacyPolicy.html";//隐私协议
    public static final String PRIVACY_LOCAL_URL = "file:///android_asset/羊驼影美用户隐私声明.html";
    public static final String USER_AGREEMENT_URL = "https://premiere.yangtuoedu.com/appPage/userAgreement.html";//用户协议
    public static final String USER_AGREEMENT_LOCAL_URL = "file:///android_asset/羊驼影美用户服务协议.html";


    public static String DIALOG_YUEGAO = "https://res.ytaxx.com/image/activity/20201126/8128170e1a78467b8fb9df414eedb4e6.png";//参与轮换的微信二维码地址
    public static int CONTRACTS_WIDTH = DensityUtil.dip2px(BaseApplication.getInstance(), 375);

    //网校合同
    public static String CONTRACTS_TITLE = "羊驼教育培训协议";
    public static String CONTRACTS_COMPANY_NAME = "湖南羊驼教育科技有限公司";
    public static String CONTRACTS_SEAL_IMG = "";

    //七牛api参数地址
    public static class VideoImageParam {
        public static final String PATH = "?vframe/jpg/offset/1";//七牛云自动过去视频地址第一帧图片
        public static final String CUSTOM_PATH = "?vframe/jpg/offset/";//七牛云自动过去视频地址第一帧图片
    }

    //gif配置
    public static class GIF {
        public static final String SHALOU = "http://oyxe80s4l.bkt.clouddn.com/image/activity/20200518/da0b28d36a184edda9ae5563a922250d.gif";
        public static final String GO_PLAY1 = "http://oyxe80s4l.bkt.clouddn.com/image/activity/20200518/4ba2c165ec03487999f70e4c4d61d092.gif";
        public static final String SHALOU_PLAY = "http://oyxe80s4l.bkt.clouddn.com/image/activity/20200518/32b6382d038a4ff8a2ee2fcf96b4bb3b.gif";
        public static final String GO_PLAY2 = "http://oyxe80s4l.bkt.clouddn.com/image/activity/20200518/b70e0685c9b649dd8617d23610514ccf.gif";
        public static final String ONLINE_PLAY = "http://oyxe80s4l.bkt.clouddn.com/image/activity/20200518/696b145b0d8d43768b8de1e74499c028.gif";
        public static final String VIDEO_PLAY_GIF = "http://res.ytaxx.com/ielts/20210701/357673b2cb4c645fb182e4595d272e69.gif";
        public static final String TEST_COVER_GIF = "http://res.ytaxx.com/ielts/20210701/41d0e299ca1abeb2094852da042165c7.gif";
        public static final String FINGER_GIF = "http://res.ytaxx.com/ielts/20210122/a579ba10685b71f67d4daaf60cd8363e.gif";
        public static final String ALPACA_PLAY = "http://res.ytaxx.com/ielts/20210114/b0baee9d279d34fa1dfd71aadb908c3f.gif";

    }

    //首页浮窗配置
    public static class FestivalAD {
        public static String IMAGE_PATH = "";
        public static String JUMP_PATH = "";
        public static String MINI_PATH = "";
        public static String BOTTOM_CONTENT = "";
        public static String EXTENT_STRING = "";
        public static int OPEN = 0;
    }

    //web分享页面配置
    public static class WebUrl {
        public static final String TEST_PLAN_URL = HttpUrl.BaseURL.BASE_URL + "static/examPlan/index.html?token=";
    }

    /**
     * 传入数据Key * BUNDLE
     */
    public static final String EXTRA_DATA_BUNDLE = "EXTRA_DATA_BUNDLE" + Bundle.class.getName();


    public static class HomeVideoClass {
        public static String BOTTOM_TEXT;
        public static String JUMP_PATH;
    }

    public static class NewVideoClass {
        public static String BOTTOM_TEXT;
        public static String JUMP_PATH;
    }


    //项目类型 1=pte 2=雅思 3=日语 4=ccl 5.韩语 6.多领国 7.通英 8.原画
    public static class Domain {
        public static final int DOMAIN_PTE = 1;
        public static final int DOMAIN_YS = 2;
        public static final int DOMAIN_RY = 3;
        public static final int DOMAIN_CCL = 4;
        public static final int DOMAIN_HY = 5;
        public static final int DOMAIN_DLG = 6;
        public static final int DOMAIN_TY = 7;
        public static final int DOMAIN_YH = 8;

        public static boolean isLimitClassTime(int domain) {
            if (domain == Domain.DOMAIN_RY || domain == Domain.DOMAIN_HY || domain == Domain.DOMAIN_TY || domain == Domain.DOMAIN_YH) {
                return true;
            }
            return false;
        }
    }


    public static int getRadomNum(int num) {
        Random ra = new Random();
        return ra.nextInt(num);
    }


    public static class User {
        public static String USER_UID = "";
        public static String USER_NAME = "";
        public static String USER_ICON = "";
        public static String WEB_TOKEN = "";
        public static int USER_SEX = 0;
        public static int USER_CLOCK = 0;
        public static long USER_CREATTIME = 0;
        public static int USER_PRON = 1;
        public static int ISMEMBERS = 0;
        public static int ISOLDMEMBERS = 0;
        public static int WORKCOUNT = 0;
        public static int LIKECOUNT = 0;
        public static int PAYMENT = 0;
        public static String USER_VIP_TIME = "";
        public static String VIP_MINGRAMERID = "";
        public static IdolBean.DataBean USER_IDOL = null;
        public static LexiconInfoBean.DataBean.LexiconListBean USER_LEXICON;
        public static LexiconInfoBean.DataBean.LexiconListBean USER_COLLECT_LEXICON;
        public static OnlineStatusBean USER_ONLINE;
        public static LexiconInfoBean.DataBean.LexiconListBean USER_RADIO_LEXICON;

    }


    public static class Net {
        public static String PTE_HOST = "https://www.ytaxx.com/";
    }


    public static class SP {
        //用户信息
        public static final String USER_UID = "USER_UID";
        public static final String USER_CREAT = "USER_CREAT";
        public static final String USER_NAME = "USER_NAME";
        public static final String USER_ICON = "USER_ICON";
        public static final String USER_SEX = "USER_SEX";
        public static final String USER_LEXICON = "USER_LEXICON";
        public static final String USER_ONLINE = "USER_ONLINE";
        public static boolean ISAUTO_PLAY = false;
        public static boolean RADIO_ISAUTO_PLAY = true;
        public static boolean QUESTION_PLAY = false;
        public static boolean QUESTION_RIGHT = true;
        public static boolean TRANS_SHOW = false;
        public static int RADIO_LOOP_TIME = 0;
        public static int RADIO_INTERVAL_TIME = 0;
        public static Float SCENE_SPEED = 1.0f;
        //同意用户隐私协议
        public static final String AGREE_PRIVACY = "AGREE_PRIVACY";


        //app配置信息
        public static final String APPCONFIG_REVIEW_WX = "APPCONFIG_REVIEW_WX";
        public static final String APPCONFIG_REVIEW_WX_PUB = "APPCONFIG_REVIEW_WX_PUB";
        public static final String APPCONFIG_DCOURSE_SEDUCE_WX = "APPCONFIG_DCOURSE_SEDUCE_WX";
        public static final String APPCONFIG_DCOURSE_SEDUCE_TEXT = "APPCONFIG_DCOURSE_SEDUCE_TEXT";
        //app配置信息
        public static final String APPCONFIG_DOWNLOAD_ICON = "APPCONFIG_DOWNLOAD_ICON";//app下载二维码地址
        public static final String APPCONFIG_ALLWINDOW = "APPCONFIG_ALLWINDOW";//各种微信弹窗，微信号配置

        //splash界面的版本号
        public static final String SPLASH_VERSION = "SPLASH_VERSION";
        //隐私协议
        public static final String USERPERMISS_VERSION = "USERPERMISS_VERSION";
        public static int SUBSCRIBEID = 0;//订阅号消息id

        //配音作品播放速度
        public static final String DUB_VIDEO_SPEED = "DUB_VIDEO_SPEED";


        //app配置信息
        public static final String APPCONFIG_DOWNLOAD_PATH = "APPCONFIG_DOWNLOAD_PATH";//app下载地址
        public static final String WXCONFIG_ALLWINDOW = "WXCONFIG_ALLWINDOW";//各种微信弹窗，微信号配置

        //HomeAD首页广告弹窗时间
        public static final String AD_HOME_TIME = "AD_HOME_TIME";

        //提示更新逻辑，相同版本的提示更新每天最多提醒1次，最多提醒3次
        //最后一次提示版本更新的时间
        public static final String UPDATE_LAST_TIME = "UPDATE_LAST_TIME";
        //最后一次提示版本更新的版本号code
        public static final String UPDATE_LAST_VERSIONCODE = "UPDATE_LAST_VERSIONCODE";
        //相同版本下提示更新的次数
        public static final String UPDATE_REMIND_NUM = "UPDATE_REMIND_NUM";

        public static Float DIALOGUE_SPEED = 1.0f;//情景对话播放速度

    }

    public static class AppConfig {
        public static String REVIEW_WX = "";
        public static String REVIEW_WX_PUB = "";
        public static final int SPLASH_VERSION = 1;//splash显示的版本号

        public static int WECHAT_LOGIN_OPEN = 0;//微信登录开关 0关1开

        public static String DOWNLOAD_TESTOVERICONURL = "";
        public static String APPCONFIG_ALLWINDOW = "";//所有的微信号配置
        public static String WXCONFIG_ALLWINDOW = "";//所有的微信号配置
        public static String MODEULE_WX = "";//MODULE微信号配置

        public static String COURSE_SEDUCE_WX = "";//公开课的微信号
        public static String COURSE_SEDUCE_TEXT = "";//公开课的分享描述

        public static int USER_EXTEND = 0;//用户信息拓展开关  0 开 1关
        public static String DOWNLOAD_PATH = "https://www.yangtuoedu.com/static/ys_app/appPage/download/download.html";//app下载地址
        public static String UPLOAD_PATH = "https://premiere.yangtuoedu.com/appPage/video_list/index.html";//上传作品地址
    }


    public static final String APK_SHARE_PATH = "";
    public static final String APK_LOGO_PATH = "http://res.ytaxx.com/ielts/20211112/a3c65c2974270fd093ee8a9bf8ae7d0b.png";
    public static final String APK_NAME = "ytPremiere.apk";
    public static final String USER_RULE = "https://ytaxx.com/appPage/privacyPolicy-pte.html";
    public static final String NUMREACTION_RULE = "http://ytaxx.com/appPage/reactionTraining/index.html";
    public static final String YS_RULE = "https://ytaxx.com/appPage/userAgreement-pte.html";


    //默认包名文件夹相对路径
    public static String PACKAGE_NAME = File.separator + "com.ytcourse.client" + File.separator;
    //默认程序绝对路径
    public static String DEFAULT_BASE_PATH = Build.VERSION.SDK_INT >= 29 ? getExternalFilesDir(BaseApplication.getInstance()).getAbsolutePath() + PACKAGE_NAME : Environment.getExternalStorageDirectory().getAbsolutePath() + PACKAGE_NAME;
    //存放发送图片的目录
    public static String PICTURE_PATH = DEFAULT_BASE_PATH + "image" + File.separator;
    //我的头像保存目录
    public static String AVATAR_DIR = DEFAULT_BASE_PATH + "avatar" + File.separator;
    //录音文件存储目录      voice/(题目id)/时间戳录音文件
    public static final String VOICE_DIR = DEFAULT_BASE_PATH + "voice" + File.separator;
    //更新下载的apk路径
    public static final String APK_DIR = DEFAULT_BASE_PATH + "apk" + File.separator;
    //日志路径
    public static final String LOACL_LOG_DIR = DEFAULT_BASE_PATH + "log" + File.separator;

    public static final String RICH_CASH = DEFAULT_BASE_PATH + "rich" + File.separator;

    //合同路径
    public static final String CONTRACT_PATH = DEFAULT_BASE_PATH + "contract" + File.separator;

    public static String getVoiceDir() {
        String path = VOICE_DIR;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }

    public static String getApkDir() {
        String path = APK_DIR;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }

    public static String getRichCashDir() {
        String path = RICH_CASH;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }

    public static String getPictureDir() {
        String path = PICTURE_PATH;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }

    public static File getPictureDirFile() {
        String path = PICTURE_PATH;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    public static String getContractDir() {
        String path = CONTRACT_PATH;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }


}
