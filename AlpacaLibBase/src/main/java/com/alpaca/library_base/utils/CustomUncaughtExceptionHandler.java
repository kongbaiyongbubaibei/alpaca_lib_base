package com.alpaca.library_base.utils;

import com.alpaca.library_base.base.BaseApplication;

/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/11/5
 */
public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        while (!BaseApplication.isDebug) {
            //阻塞，防止闪退，迫使程序未响应
        }
    }
}
