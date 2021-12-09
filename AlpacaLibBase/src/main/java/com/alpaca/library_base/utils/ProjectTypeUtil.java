package com.alpaca.library_base.utils;

import com.alpaca.library_base.base.BaseApplication;

/**
 * @文件名: ProjectTypeUtil
 * @功能描述:
 * @Date : 2021/4/20
 * @email:
 * @修改记录:
 */
public class ProjectTypeUtil {

    public static int getProjectType() {
        switch (AppUtils.getPackageInfo(BaseApplication.getInstance()).packageName) {
            case "com.ytekorean.client":
                return 5;
            case "com.ytejapan.client":
                return 3;
            case "com.ieltsdu.client":
                return 2;
            case "com.yteduge.client":
                return 7;
            case "com.ytepaint.client":
                return 8;
            case "com.ytpremiere.client":
                return 9;
            case "com.ytedu.client":
                return 1;
            default:
                return -1;
        }
    }

    public static String getProjectName(String sg) {
        switch (sg) {
            case "1":
                return "羊驼PTE";
            case "2":
                return "羊驼雅思";
            case "3":
                return "羊驼日语";
            case "4":
                return "ccl";
            case "5":
                return "羊驼韩语";
            case "6":
                return "多邻国";
            case "7":
                return "羊驼英语";
            case "8":
                return "羊驼CG绘画";
            case "9":
                return "";
            case "10":
                return "";

            default:
                return "";
        }
    }
}
