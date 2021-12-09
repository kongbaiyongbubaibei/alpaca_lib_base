package com.alpaca.library_base.manager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.alpaca.library_base.R;
import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.widgets.GlideRoundTransform;

import java.io.File;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class ImageLoader {

    protected static ImageLoader mInstance;

    private ImageLoader() {
    }

    public static ImageLoader with() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                mInstance = new ImageLoader();
            }
        }
        return mInstance;
    }

    public void loadPicture(ImageView target, String path, @DrawableRes int defaultRes, int errorRes) {
        RequestOptions options = new RequestOptions()
                .placeholder(defaultRes)
                .error(errorRes);
        Glide.with(BaseApplication.getInstance())
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .into(target);
    }

    public void loadPicture(ImageView target, String path) {
        Glide.with(BaseApplication.getInstance())
                .load(path)
                .into(target);
    }

    public void loadPicture(ImageView target, File path) {
        Glide.with(BaseApplication.getInstance())
                .load(path)
                .error(R.drawable.default_hejinei)
                .into(target);
    }

    public void loadBigPicture(ImageView target, String path) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .format(DecodeFormat.PREFER_RGB_565);
        Glide.with(BaseApplication.getInstance())
                .load(path)
                .apply(options)
                .into(target);
    }

    public void loadGifPicture(ImageView target, String path) {
        Glide.with(BaseApplication.getInstance())
                .load(path)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (resource instanceof GifDrawable) {
                            //加载一次
                            ((GifDrawable) resource).setLoopCount(-1);
                        }
                        return false;
                    }
                })
                .into(target);
    }

    /**
     * 加载gif图
     */
    public void loadGifImage(int url, ImageView imageView) {
        Glide.with(BaseApplication.getInstance())
                .load(url)
                .into(imageView);
    }

    public void loadPictureRoundCorners(ImageView target, String path, int roundingRadius, @DrawableRes int defaultRes, int errorRes) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.HIGH)
                .dontAnimate()
                .placeholder(defaultRes)
                .error(errorRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CenterCrop(), new RoundedCorners(roundingRadius));
        Glide.with(BaseApplication.getInstance())
                .load(path)
                .apply(options)
                .into(target);
    }

    public void loadPictureRoundCorners(ImageView target, String path, int roundingRadius) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.HIGH)
                .dontAnimate()
                .error(R.drawable.default_hejinei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new CenterCrop(), new RoundedCorners(roundingRadius));
        Glide.with(BaseApplication.getInstance())
                .load(path)
                .apply(options)
                .into(target);
    }

    /**
     * 设置顶部圆角
     */
    public void loadPictureRoundTopCorners(final ImageView target, String path, int roundingRadius) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.HIGH)//优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存策略
                .transform(new GlideRoundTransform(roundingRadius));//转化为圆角
        Glide.with(BaseApplication.getInstance()).load(path).apply(options).into(target);
    }

    public void loadPictureCircleCrop(ImageView target, String path, @DrawableRes int defaultRes, int errorRes) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.HIGH)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new CircleCrop())
                .placeholder(defaultRes)
                .error(errorRes);

        Glide.with(BaseApplication.getInstance())
                .load(path)
                .apply(options)
                .into(target);
    }

    public void loadPictureCircleCrop(ImageView target, String path) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.HIGH)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new CircleCrop())
                .error(R.drawable.default_hejinei);

        Glide.with(BaseApplication.getInstance())
                .load(path)
                .apply(options)
                .into(target);
    }

    /**
     * 设置背景
     */
    public void loadImageInBackGround(final View view, String url) {
        Glide.with(BaseApplication.getInstance()).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                view.setBackground(resource);
            }
        });
    }

    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    public static void clearFileCache(Context context) {
        Glide.get(context).clearDiskCache();
    }
}
