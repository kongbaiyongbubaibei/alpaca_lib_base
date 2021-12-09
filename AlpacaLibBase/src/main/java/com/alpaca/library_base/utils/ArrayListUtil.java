package com.alpaca.library_base.utils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * @文件名: ArrayListUtil
 * @功能描述:
 * @Date : 2021/3/16
 * @email:
 * @修改记录:
 */
public class ArrayListUtil {

    public static boolean isNotEmpty(@Nullable List arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isUseful(@Nullable List arrayList, int size) {
        if (arrayList != null && arrayList.size() > size) {
            return true;
        }
        return false;
    }

}
