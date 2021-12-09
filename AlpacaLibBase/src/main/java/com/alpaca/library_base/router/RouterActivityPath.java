package com.alpaca.library_base.router;

/**
 * 用于组件开发中，ARouter单Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */

public class RouterActivityPath {
    /**
     * 主业务组件
     */
    public static class Main {
        private static final String MAIN = "/main";
        /*主业务界面*/
        public static final String PAGER_MAIN = MAIN + "/Main";

        public static final String PAGER_LOGIN = MAIN + "/Login";
        public static final String PAGER_ONELOGIN = MAIN + "/ONELOGIN";
    }

    public static class Web {
        private static final String MAIN = "/web";
        /*主业务界面*/
        public static final String PAGER_WEB = MAIN + "/Web";
    }

    public static class Welfare {
        private static final String MAIN = "/Welfare";
        /*主业务界面*/
        public static final String PAGER_WELFARE = MAIN + "/Welfare";
        public static final String PAGER_RECEIVE = MAIN + "/Receive";
    }

    public static class NetSchool {
        private static final String MAIN = "/NetSchool";
        public static final String PAGER_MAIN = MAIN + "/NetSchoolMain";

        public static final String PLAY_VIDEO = MAIN + "/PlayVideo";


    }

    /**
     * 练习页面业务组件
     */
    public static class Practice {
        private static final String PRACTICE = "/practice";
        /*主业务界面*/
        public static final String PRACT_RA = PRACTICE + "/RAAAActivity";
        public static final String PRACT_RS = PRACTICE + "/RSSActivity";
        public static final String PRACT_DI = PRACTICE + "/DIIActivity";
        public static final String PRACT_RL = PRACTICE + "/RLLActivity";
        public static final String PRACT_ASQ = PRACTICE + "/ASQQActivity";
        public static final String PRACT_RFIB = PRACTICE + "/RFIBActivity";
        public static final String PRACT_RWFIB = PRACTICE + "/RWFIBActivity";
        public static final String PRACT_ROP = PRACTICE + "/ROPActivity";
        public static final String PRACT_CSA = PRACTICE + "/CSAActivity";
        public static final String PRACT_CMA = PRACTICE + "/CMAActivity";
        public static final String PRACT_SST = PRACTICE + "/SSTActivity";
        public static final String PRACT_HIW = PRACTICE + "/HIWActivity";
        public static final String PRACT_WFD = PRACTICE + "/WFDActivity";
        public static final String PRACT_FIB = PRACTICE + "/FIBActivity";
        public static final String PRACT_MCM = PRACTICE + "/MCMActivity";
        public static final String PRACT_HCS = PRACTICE + "/HCSActivity";
        public static final String PRACT_MCS = PRACTICE + "/MCSActivity";
        public static final String PRACT_SMW = PRACTICE + "/SMWActivity";
        public static final String PRACT_WE = PRACTICE + "/WEActivity";
        public static final String PRACT_SWT = PRACTICE + "/SWTActivity";
    }


    /**
     * 用户组件
     */
    public static class User {
        private static final String USER = "/user";

        /*登录界面*/
        public static final String PAGER_LOGIN = USER + "/Login";
        /*登录界面*/
        public static final String PAGER_RESET = USER + "/Reset";

        /*用户详情*/
        public static final String PAGER_USERDETAIL = USER + "/UserDetail";
    }
}
