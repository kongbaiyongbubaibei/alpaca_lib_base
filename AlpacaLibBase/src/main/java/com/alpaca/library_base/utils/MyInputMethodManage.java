package com.alpaca.library_base.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * @文件名: MyInputMethodManage
 * @功能描述:
 * @Date : 2020/11/17
 * @email:
 * @修改记录:
 */
public class MyInputMethodManage {
    public static void outMethod(Activity context) {

    }

    public static void hideMethod(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
