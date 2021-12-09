package com.alpaca.library_base.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @文件名: HookUtils
 * @功能描述:
 * @Date : 2021/9/18
 * @email:
 * @修改记录:
 */
public class HookUtils {

    private static final String TAG  ="HookUtils";

    public static void hookPM() {
        try {
            //获取ActivityThread对象
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field field = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            field.setAccessible(true);
            Object activityThread = field.get(null);

            //获取了PackageManager对象
            Method getPackageManager = activityThread.getClass().getDeclaredMethod("getPackageManager");
            getPackageManager.setAccessible(true);
            Object iPackageManager = getPackageManager.invoke(activityThread);

            //获取IPackageManager接口的Class对象
            Class<?> iPackageManagerClass = Class.forName("android.content.pm.IPackageManager");

            //生成动态代理类
            Object pmProxy = Proxy.newProxyInstance(
                    Thread.currentThread().getContextClassLoader(), //加载代理类的类加载器
                    new Class[]{iPackageManagerClass}, //代理类需要实现的接口 （需要实现被代理类的接口）
                    (proxy, method, args) -> { // 当被代理类的接口实现方法被调用时，会回调此实现类的invoke()方法，该方法中应该保证被代理类的原逻辑的执行（即 return时的调用）
                        if (method.getName().equals("getPackageInfo")){
                            Log.i(TAG, "invoke|getPackageInfoCount: "+System.currentTimeMillis());
                            StackTraceElement st[] = Thread.currentThread().getStackTrace();
                            for (int i = 0; i < st.length; i++) {
                                Log.d(TAG, "trace: " + st[i].toString());
                            }
                        }
                        /**
                         * 被代理类之前的执行逻辑，总是会由代理类通过回调调用到此处
                         *  proxy： 代理对象（在代理类中通过 this 传递过来）
                         *  method：通过iPackageManagerClass接口反射拿到的接口方法
                         *  args： 调用接口方法时，传入方法中的参数数组
                         *  我们可以通过在此处添加自己的逻辑，也可以通过改变参数和返回值来改变原有的逻辑
                         * */

                        /**
                         * 此处的iPackageManager为被代理对象，此方法保证了原逻辑的执行
                         * 返回值：method方法执行后的返回值
                         * */
                        return method.invoke(iPackageManager, args);
                    });

            /**
             * 获取ActivityThread下PackageManager的引用，并将代理对象赋值给此引用
             * 此后在程序的运行中，该ActivityThread的sPackageManager属性总是此代理类，
             * 方法被调用时，通过InvocationHandler.invoke方法来实现逻辑
             * */
            Field sPackageManagerField = activityThreadClass.getDeclaredField("sPackageManager");
            sPackageManagerField.setAccessible(true);
            sPackageManagerField.set(activityThread,pmProxy);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
