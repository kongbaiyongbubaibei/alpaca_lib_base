package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alpaca.library_base.R;
import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.base.activity.BaseActivity;
import com.alpaca.library_base.base.fragment.BaseFragment;
import com.alpaca.library_base.event.FirstBackEvent;
import com.alpaca.library_base.event.GetWeChatEvent;
import com.alpaca.library_base.event.UserCustomEvent;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.ut.device.UTDevice;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import androidx.annotation.RequiresApi;

/**
 * @文件名: WxShareUtil
 * @功能描述:
 * @Date : 2018/3/8
 * @email:
 * @修改记录:
 */
public class WxShareUtil {

    private static String TAG = "WxShareUtil";

    public static void shareUrl(Context context, String title, String desc, String url, int id, String i) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webPage);
        msg.title = title;
        String introStr;
        if (desc != null && desc.length() > 1000) {
            introStr = desc.substring(0, 1000);
        } else {
            introStr = desc;
        }
        msg.description = introStr;

        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), id);
        msg.thumbData = WxUtil.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;//
        BaseApplication.iwxapi.sendReq(req);
    }

    public static void shareUrl(Context context, String title, String desc, String url) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webPage);
        msg.title = title;
        String introStr;
        if (desc != null && desc.length() > 1000) {
            introStr = desc.substring(0, 1000);
        } else {
            introStr = desc;
        }
        msg.description = introStr;

        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_share);
        msg.thumbData = WxUtil.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;//
        BaseApplication.iwxapi.sendReq(req);
    }

    public static void shareUrl(Context context, String title, String desc, String url, int type) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webPage);
        msg.title = title;
        String introStr;
        if (desc != null && desc.length() > 1000) {
            introStr = desc.substring(0, 1000);
        } else {
            introStr = desc;
        }
        msg.description = introStr;

        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_share);
        msg.thumbData = WxUtil.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneSession;//
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//
        }
        BaseApplication.iwxapi.sendReq(req);
    }

    public static void shareUrl(Context context, String title, String desc, String url, int type, int id) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webPage);
        msg.title = title;
        String introStr;
        if (desc != null && desc.length() > 1000) {
            introStr = desc.substring(0, 1000);
        } else {
            introStr = desc;
        }
        msg.description = introStr;

        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), id);
        msg.thumbData = WxUtil.bmpToByteArray(thumb, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneSession;//
        } else {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;//
        }
        BaseApplication.iwxapi.sendReq(req);
    }

    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static void shareAppMasg(Context context, int id, String title, String des) {
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.webpageUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.ytedu.client&fromcase=40002";
        miniProgram.userName = "gh_89e72d1b9a7c";
        String path = "../detail/detail?id=" + id;
        String url = null;
        try {
            url = URLEncoder.encode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        miniProgram.path = "pages/index/index?isShare=true&paraUrl=" + url;
        miniProgram.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;

        String introStr;
        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        msg.title = title;
        if (des.length() > 1000) {
            introStr = des.substring(0, 1000);
        } else {
            introStr = des;
        }
        msg.description = introStr;

        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_share);
        msg.thumbData = WxUtil.bmpToByteArray(bmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("miniProgram");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        BaseApplication.iwxapi.sendReq(req);
    }

    public static void shareAppExp(Context context, int id, int type, int cId, String title, String des) {
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.webpageUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.ytedu.client&fromcase=40002";
        miniProgram.userName = "gh_89e72d1b9a7c";
        String path = "../ItemContent/ItemContent?id=" + id + "&ListType=" + type + "&categoriesId=" + cId;
        String url = null;
        try {
            url = URLEncoder.encode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        miniProgram.path = "pages/index/index?isShare=true&paraUrl=" + url;
        miniProgram.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;

        String introStr;
        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        msg.title = title;
        if (des.length() > 1000) {
            introStr = des.substring(0, 1000);
        } else {
            introStr = des;
        }
        msg.description = introStr;

        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_share);
        msg.thumbData = WxUtil.bmpToByteArray(bmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("miniProgram");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        BaseApplication.iwxapi.sendReq(req);
    }

    public static void shareAppTest(BaseActivity context, int id, String type, String title, String des) {
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.webpageUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.ytedu.client&fromcase=40002";
        miniProgram.userName = "gh_89e72d1b9a7c";
        String path = "../recordShare/record?id=" + id + "&type=" + type;
        String url = null;
        try {
            url = URLEncoder.encode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        miniProgram.path = "pages/index/index?isShare=true&paraUrl=" + url;
        miniProgram.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;

        String introStr;
        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        msg.title = title;
        if (des.length() > 1000) {
            introStr = des.substring(0, 1000);
        } else {
            introStr = des;
        }
        msg.description = introStr;

        View dView = context.getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());

        if (bitmap != null) {
            msg.thumbData = WxUtil.bmpToByteArray(comp(bitmap), true);
            bitmap.recycle();
        } else {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_share);
            msg.thumbData = WxUtil.bmpToByteArray(bmp, true);
            bmp.recycle();
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("miniProgram");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        BaseApplication.iwxapi.sendReq(req);


    }

    public static void shareAppDy(BaseActivity context, int id, int type, String title, String des) {
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.webpageUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.ytedu.client&fromcase=40002";
        miniProgram.userName = "gh_89e72d1b9a7c";
        String path = "../communityDetail/communityDetail?parent=" + type + "&dynamicId=" + id;
        String url = null;
        try {
            url = URLEncoder.encode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        miniProgram.path = "pages/index/index?isShare=true&paraUrl=" + url;
        miniProgram.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;

        String introStr;
        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        msg.title = title;
        if (des.length() > 1000) {
            introStr = des.substring(0, 1000);
        } else {
            introStr = des;
        }
        msg.description = introStr;

        View dView = context.getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());

        if (bitmap != null) {
            msg.thumbData = WxUtil.bmpToByteArray(comp(bitmap), true);
            bitmap.recycle();
        } else {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_share);
            msg.thumbData = WxUtil.bmpToByteArray(bmp, true);
            bmp.recycle();
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("miniProgram");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        BaseApplication.iwxapi.sendReq(req);

    }

    public static void shareAppTest(BaseFragment context, int id, String type, String title, String des) {
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.webpageUrl = "http://a.app.qq.com/o/simple.jsp?pkgname=com.ytedu.client&fromcase=40002";
        miniProgram.userName = "gh_89e72d1b9a7c";
        String path = "../recordShare/record?id=" + id + "&type=" + type;
        String url = null;
        try {
            url = URLEncoder.encode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        miniProgram.path = "pages/index/index?isShare=true&paraUrl=" + url;
        miniProgram.miniprogramType = WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE;

        String introStr;
        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        msg.title = title;
        if (des.length() > 1000) {
            introStr = des.substring(0, 1000);
        } else {
            introStr = des;
        }
        msg.description = introStr;

        View dView = context.getActivity().getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());

        if (bitmap != null) {
            msg.thumbData = WxUtil.bmpToByteArray(comp(bitmap), true);
            bitmap.recycle();
        } else {
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_share);
            msg.thumbData = WxUtil.bmpToByteArray(bmp, true);
            bmp.recycle();
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("miniProgram");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        BaseApplication.iwxapi.sendReq(req);


    }

    public static boolean isWxInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                String vn = pinfo.get(i).versionName;
                if (pn.equals("com.tencent.mm")) {
                    Log.i("", "isWxInstall0000: " + vn);
                    int i0 = 0;
                    for (int i1 = 0; i1 < vn.toCharArray().length; i1++) {
                        if (vn.toCharArray()[i1] == '.') {
                            i0++;
                        }
                    }
                    if (i0 == 2) {
                        if (Integer.parseInt(vn.substring(0, vn.indexOf("."))) <= 6) {
                            if (Integer.parseInt(vn.substring(vn.indexOf(".") + 1, vn.indexOf(".", 2))) <= 5) {
                                if (Integer.parseInt(vn.substring(vn.indexOf(".", 2) + 1)) <= 6) {
                                    return false;
                                } else {
                                    return true;
                                }
                            } else {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        if (Integer.parseInt(vn.substring(0, vn.indexOf("."))) <= 6) {
                            if (Integer.parseInt(vn.substring(vn.indexOf(".") + 1, vn.indexOf(".", 2))) <= 5) {
                                if (Integer.parseInt(vn.substring(vn.indexOf(".", 2) + 1, vn.indexOf(".", 3))) <= 6) {
                                    return false;
                                } else {
                                    return true;
                                }
                            } else {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 10, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 450f;//这里设置高度为800f
        float ww = 450f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;//压缩好比例大小后再进行质量压缩
    }

    public static void sharePicToWx(BaseActivity context, int type) {
        WXMediaMessage msg = new WXMediaMessage();
        View dView = context.getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());

        WXImageObject imageObject = new WXImageObject(bitmap);
        msg.mediaObject = imageObject;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 125, 125, true);
        bitmap.recycle();
        msg.thumbData = WxUtil.bmpToByteArray(thumbBmp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        BaseApplication.iwxapi.sendReq(req);
//        Message message = Message.obtain(context.mHandler,812);
//        message.sendToTarget();
//        if (bitmap!=null){
//            msg.thumbData = WxUtil.bmpToByteArray(comp(bitmap),true);
//            bitmap.recycle();
//        }else {
//            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
//            msg.thumbData = WxUtil.bmpToByteArray(bmp,true);
//            bmp.recycle();
//        }

    }

    public static void sharePicToWx(int type, Bitmap bitmap) {
        if (bitmap == null) return;
        WXMediaMessage msg = new WXMediaMessage();

        WXImageObject imageObject = new WXImageObject(bitmap);
        msg.mediaObject = imageObject;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 160, 252, true);

        msg.thumbData = WxUtil.bmpToByteArray(thumbBmp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = msg;
        if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        BaseApplication.iwxapi.sendReq(req);
//        if (bitmap!=null){
//            msg.thumbData = WxUtil.bmpToByteArray(comp(bitmap),true);
//            bitmap.recycle();
//        }else {
//            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
//            msg.thumbData = WxUtil.bmpToByteArray(bmp,true);
//            bmp.recycle();
//        }

    }



    public static void openMiniProgram(String path) {
        Log.i(TAG, "openMiniProgram: " + path);
        if (AppUtils.isWeixinAvilible(BaseApplication.getInstance())) {
            EventBus.getDefault().post(new UserCustomEvent("counselor"));
            if (!TextUtils.isEmpty(path)) {
                openWx(BaseApplication.getInstance());
                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                req.userName = "gh_23cd60274ee6"; // 小程序原始id
                path = path + "&sourceType=" + UserInfoUtils.getUserInfo().getUserType() + "&sourceBazaar=" + MateDataUtils.getChannelCode(BaseApplication.getInstance()) + "&uuid=" + UTDevice.getUtdid(BaseApplication.getInstance());
                req.path = path; //拉起小程序页面的可带参路径，不填默认拉起小程序首页
                Log.i(TAG, "openMiniProgram: " + req.path);
                req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
                BaseApplication.iwxapi.sendReq(req);
            } else {
                EventBus.getDefault().post(new GetWeChatEvent());
                Toast.makeText(BaseApplication.getInstance(), "未获取到跳转信息", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(BaseApplication.getInstance(), "您还未安装微信", Toast.LENGTH_SHORT).show();
        }
    }


    public static void openMiniProgram(String path, String extendString) {
        if (AppUtils.isWeixinAvilible(BaseApplication.getInstance())) {
            EventBus.getDefault().post(new UserCustomEvent("counselor", extendString));
            EventBus.getDefault().post(new FirstBackEvent());
            if (!TextUtils.isEmpty(path)) {
                openWx(BaseApplication.getInstance());
                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                req.userName = "gh_23cd60274ee6"; // 小程序原始id
                path = path + "&sourceType=" + UserInfoUtils.getUserInfo().getUserType() + "&sourceBazaar=" + MateDataUtils.getChannelCode(BaseApplication.getInstance()) + "&uuid=" + UTDevice.getUtdid(BaseApplication.getInstance());
                req.path = path; //拉起小程序页面的可带参路径，不填默认拉起小程序首页
                Log.i(TAG, "openMiniProgram: " + req.path);
                req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
                BaseApplication.iwxapi.sendReq(req);
            } else {
                EventBus.getDefault().post(new GetWeChatEvent());
                Toast.makeText(BaseApplication.getInstance(), "未获取到跳转信息", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(BaseApplication.getInstance(), "您还未安装微信", Toast.LENGTH_SHORT).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static void openWx(Context activity) {
        try {
            if (AppUtils.isWeixinAvilible(activity)) {
                Intent lan = activity.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (lan != null) {
                    intent.setComponent(lan.getComponent());
                    activity.startActivity(intent);
                }
            } else {
                Toast.makeText(activity, "您还未安装微信", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.i(TAG, "openWx: " + e.getLocalizedMessage());
            Log.i(TAG, "openWx: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
