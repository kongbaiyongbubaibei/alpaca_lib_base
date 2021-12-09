package com.alpaca.library_base.router;

/**
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */

public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        private static final String HOME = "/home";
        /*首页*/
        public static final String PAGER_HOME = HOME + "/Home";
    }


    /**
     * 用户组件
     */
    public static class User {
        private static final String USER = "/user";
        /*我的*/
        public static final String PAGER_ME = USER + "/Me";

    }

    /**
     * 经验组件
     */
    public static class Experience {
        private static final String Experience = "/experience";
        /*经验页*/
        public static final String PAGER_ExperienceMain = Experience + "/WalletMain";

    }
    /**
     * 应用组件
     */
    public static class Insurance {
        private static final String Wallet = "/insurance";
        /*应用*/
        public static final String PAGER_InsuranceMain = Wallet + "/Insurance";

    }

    public static class NetSchool {
        private static final String MAIN = "/NetSchool";
        /*网校*/
        public static final String PAGER_EXCLUSIVE = MAIN + "/Exclusive";

    }
}
