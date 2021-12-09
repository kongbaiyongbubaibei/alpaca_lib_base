package com.alpaca.library_base.base.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.alpaca.library_base.base.presenter.BasePresenter;
import com.alpaca.library_base.base.view.IBaseView;
import com.alpaca.library_base.utils.ShowPicturesDialog;
import com.alpaca.library_base.widgets.ninegrid.ImageInfo;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWebViewActivity<T extends BasePresenter> extends BasePickActivity<T> implements IBaseView {

    private FrameLayout fullscreenContainer;
    private boolean isLoad = false;
    private boolean clickPicture = true;

    public void setClickPicture(boolean clickPicture) {
        this.clickPicture = clickPicture;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView(WebView mWebView) {
        if (mWebView != null) {
            WebSettings webSettings = mWebView.getSettings();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }

//            WebView.setWebContentsDebuggingEnabled(true);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setBlockNetworkImage(false);
            webSettings.setLoadsImagesAutomatically(true);
            // 设置允许JS弹窗
            webSettings.setUserAgentString(webSettings.getUserAgentString() + "alpacapaint");//增加浏览器打开标识
            webSettings.setDomStorageEnabled(true);

            webSettings.setDatabaseEnabled(true);
            final String dbPath = getApplicationContext().getDir("db", Context.MODE_PRIVATE).getPath();
            webSettings.setDatabasePath(dbPath);

            webSettings.setAppCacheEnabled(true);
            final String cachePath = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
            webSettings.setAppCachePath(cachePath);
            webSettings.setAppCacheMaxSize(5 * 1024 * 1024);


            webSettings.setMediaPlaybackRequiresUserGesture(false);
            webSettings.setUseWideViewPort(true); // 关键点
            webSettings.setAllowFileAccess(true); // 允许访问文件
            webSettings.setSupportZoom(true); // 支持缩放
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

            mWebView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView webView, String s) {
                    if (!isLoad) {
                        mWebView.reload();
                        isLoad = true;
                    }
                    mWebView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mWebView != null) {
                                mWebView.measure(0, 0);
                                ViewGroup.LayoutParams params = mWebView.getLayoutParams();
                                params.height = mWebView.getMeasuredHeight();
                                mWebView.setLayoutParams(params);
                            }
                            showNormal();
                        }
                    }, 500);
                    super.onPageFinished(webView, s);
                }
            });

            mWebView.setWebChromeClient(new WebChromeClient() {

                View myVideoView;

                @Override
                public void onShowCustomView(View view, CustomViewCallback callback) {
                    FrameLayout normalView = (FrameLayout) getWindow().getDecorView();
                    fullscreenContainer = new FullscreenHolder(getContext());
                    fullscreenContainer.addView(view);
                    normalView.addView(fullscreenContainer);
                    myVideoView = view;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//播放时横屏幕，如果需要改变横竖屏，只需该参数就行了
                }


                @Override
                public void onHideCustomView() {
                    FrameLayout decor = (FrameLayout) getWindow().getDecorView();
                    decor.removeView(fullscreenContainer);
                    fullscreenContainer = null;
                    myVideoView = null;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//不播放时竖屏
                }

                @Override
                public void onProgressChanged(WebView webView, int i) {
//                    if (i == 100) {
//                        mWebView.getSettings().setBlockNetworkImage(false);
//                    }
                }
            });
        }
    }

    class JsOperation {

        Activity mActivity;

        private List<String> list_imgs = new ArrayList<>();
        private int index = 0;

        private JsOperation(Activity activity) {
            mActivity = activity;
        }

        @JavascriptInterface
        public void openImage(String img) {
            list_imgs.clear();
            list_imgs.add(img);
            Log.i(TAG, "openImage: " + img);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<ImageInfo> imageInfo = new ArrayList<>();
                    for (int i = 0; i < list_imgs.size(); i++) {
                        ImageInfo info = new ImageInfo();
                        info.setThumbnailUrl(list_imgs.get(i));
                        info.setBigImageUrl(list_imgs.get(i));
                        imageInfo.add(info);
                    }
                    ShowPicturesDialog.showPictures(getContext(), imageInfo, index);
                }
            });
        }


        @JavascriptInterface
        public void webfinish() {
            finish();
        }


    }

    public void hideView(WebView webView) {
        webView.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName(\"img\"); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "    objs[i].onclick=function()  " + "    {  "
                + "        window.imagelistner.openImage(this.src);  "
                + "    }  " + "}" + "})()");
        webView.loadUrl("javascript:function setTop(){document.querySelector('.relate_mod_transition').style.display=\"none\";}setTop();");
//        webView.loadUrl("javascript:function setBottom(){document.querySelector('.rich_media_meta_list').style.display=\"none\";}setBottom();");
//        webView.loadUrl("javascript:function setTitle(){document.querySelector('.rich_media_title').style.display=\"none\";}setTitle();");
    }

    /**
     * 全屏容器界面
     */
    public static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

}

