# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile



-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-printmapping build/outputs/mapping/release/mapping.txt

-repackageclasses ''
-allowaccessmodification
-keepattributes *Annotation*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.contentTextView.BroadcastReceiver
-keep public class * extends android.contentTextView.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.billing.IInAppBillingService
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.view.View {
    public <init>(android.contentTextView.Context);
    public <init>(android.contentTextView.Context, android.util.AttributeSet);
    public <init>(android.contentTextView.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.contentTextView.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.contentTextView.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.contentTextView.Context {
    public void *(android.view.View);
    public void *(android.view.MenuItem);
}
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class **.R$*

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static void dropTable(org.greenrobot.greendao.database.Database, boolean);
    public static void createTable(org.greenrobot.greendao.database.Database, boolean);
}

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }

-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }

-keep class android.util.** { *; }
-keep interface android.util.** { *; }

#去除log输出
#-assumenosideeffects class android.util.Log{
#    public static *** v(...);
#    public static *** d(...);
#    public static *** i(...);
#    public static *** w(...);
#    public static *** e(...);
#}

-keepclassmembers enum * {   # 枚举类不能去混淆.

    public static **[] values();

    public static ** valueOf(java.lang.String);

}

#####################################
######### 主程序不能混淆的代码 #########
#####################################

-dontwarn com.client.ytkorean.library_base.utils.**
-keep class com.alpaca.library_base.utils.** { *; }
-dontwarn com.client.ytkorean.library_base.module.**
-keep class com.alpaca.library_base.module.** { *; }
-dontwarn com.client.ytkorean.library_base.constans.**
-keep class com.alpaca.library_base.constans.** { *; }
-dontwarn com.client.ytkorean.library_base.widgets.**
-keep class com.alpaca.library_base.widgets.** { *; }
-keep class com.alpaca.netschool.module.** { *; }


#####################################
########### 不优化泛型和反射 ##########
#####################################

-keepattributes Signature

#保留行号.不然会导致bugly跟踪不了
-keepattributes SourceFile,LineNumberTable

#Eventbus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
##---------------End: proguard configuration for Gson  ----------

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * implements java.io.Serializable {*;}

#md dialog
-dontwarn com.github.afollestad.material-dialogs.**
-keep class com.github.afollestad.material-dialogs.** { *; }
-dontwarn me.zhanghai.android.materialprogressbar.**
-keep class me.zhanghai.android.materialprogressbar.** { *; }

#logger
-dontwarn com.orhanobut.**
-keep class com.orhanobut.** { *; }

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

-dontwarn com.squareup.**
-keep class com.squareup.** { *; }

#okgo
-dontwarn com.lzy.okgo.**
-keep class com.lzy.okgo.**{*;}

#UMeng

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.zui.** {*;}
-keep class com.miui.** {*;}
-keep class com.heytap.** {*;}
-keep class a.** {*;}
-keep class com.vivo.** {*;}

-dontwarn com.umeng.**
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**
-keepattributes *Annotation*
-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class com.meizu.** {*;}
-keep class org.apache.thrift.** {*;}
-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}
-keep public class **.R$*{ public static final int *; }
-keep class com.uc.**{*;}


#Chivox
-keep public class com.chivox.**{*;}
#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#WxApi
-keep class com.tencent.mm.opensdk.** {
*;
}
-keep class com.tencent.wxop.** {
*;
}
-keep class com.tencent.mm.sdk.** {
*;
}

#GrenDao
-keep class org.greenrobot.greendao.**{*;}
-keep public interface org.greenrobot.greendao.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class net.sqlcipher.database.**{*;}
-keep public interface net.sqlcipher.database.**
-dontwarn net.sqlcipher.database.**
-dontwarn org.greenrobot.greendao.**

#databind
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }

#Retrofit2Life
-dontwarn com.trello.rxlifecycle2.**
-keep class com.trello.rxlifecycle2.** { *; }

-dontwarn com.dremliner.rvhelper.**
-keep class com.dremliner.rvhelper.** { *; }

-dontwarn retrofit2.**
-keep class retrofit2.** { *;}
#qiniu
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}
-ignorewarnings

# keep annotated by NotProguard
-keep @cn.trinea.android.common.annotation.NotProguard class * {*;}
-keep class * {
    @cn.trinea.android.common.annotation.NotProguard <fields>;
}
-keepclassmembers class * {
    @cn.trinea.android.common.annotation.NotProguard <methods>;
}
-keep public class com.ytejapanese.client.ui.base.AppBarLayoutBehavior { *; }
-keep public class com.ytejapanese.client.ui.activity.webview.ClassesFragment { *; }
##XBanner 图片轮播混淆配置
-keep class com.stx.xhb.xbanner.**{*;}
-ignorewarnings
#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }
#qq分享
-keep class * extends android.app.Dialog
#ijk
-keep class tv.danmaku.ijk.media.player.**{*;}
#GSYMediaplayer
-keep class com.shuyu.gsyvideoplayer.video.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.**
-keep class com.shuyu.gsyvideoplayer.video.base.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.base.**
-keep class com.shuyu.gsyvideoplayer.utils.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.utils.**
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#一键登录
-dontwarn com.geetest.onelogin.**
-keep class com.geetest.onelogin.** {
*;
}
-dontwarn com.cmic.sso.sdk.**
-keep class com.cmic.sso.sdk.** {
*;
}
-dontwarn com.unicom.xiaowo.login.**
-keep class com.unicom.xiaowo.login.** {
*;
}
-dontwarn cn.com.chinatelecom.account.**
-keep class cn.com.chinatelecom.account.** {
*;
}
#ffmpeg合成so
-dontwarn io.microshow.rxffmpeg.**
-keep class io.microshow.rxffmpeg.**{*;}
#高斯模糊
-keep class android.support.v8.renderscript.** { *; }
-keep class androidx.renderscript.** { *; }

#arouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
