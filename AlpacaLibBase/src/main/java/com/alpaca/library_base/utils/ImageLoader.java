package com.alpaca.library_base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.alpaca.library_base.R;

import java.io.File;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

public class ImageLoader {

    private Context mContext;

    private ImageLoader(Context context) {
        mContext = context;
    }

    public static ImageLoader with(Context context) {
        return new ImageLoader(context);
    }

    public void loadPicture(ImageView target, String path, @DrawableRes int defaultRes, int errorRes) {
        RequestOptions options = new RequestOptions()
                .placeholder(defaultRes)
                .error(errorRes);
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
                .transition(new DrawableTransitionOptions().crossFade())
                .apply(options)
                .into(target);
    }

    public void loadPicture(ImageView target, String path) {
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
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
    public void loadGifPicture(ImageView target, String path) {
        Glide.with(mContext)
                .asGif()
                .load(path)
                .into(target);
    }

    public void loadPictureAsBitmap(ImageView target, String path) {
        Glide.with(mContext)
                .asBitmap()
                .format(DecodeFormat.PREFER_RGB_565)
                .load(path)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.default_2)
                .error(R.drawable.default_1)
                .into(target);
    }
    public void loadPictureAsBitmap(ImageView target, String path, RequestListener<Bitmap> listener) {
        Glide.with(mContext)
                .asBitmap()
                .load(path)
                .skipMemoryCache(false)
                .listener(listener)
                .transition(new BitmapTransitionOptions())
                .into(target);

    }

    public void loadPictureThumb(ImageView target, String path, float thumb) {
        Glide.with(mContext)
                .asBitmap()
                .load(path)
                .placeholder(R.drawable.default_2)
                .error(R.drawable.default_1)
                .thumbnail(thumb)
                .skipMemoryCache(false)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .transition(new BitmapTransitionOptions())
                .into(target);

    }

    public void loadPicture(ImageView target, File file) {
        Glide.with(mContext)
                .load(file)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false)
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
                .dontAnimate()
                .transition(new DrawableTransitionOptions().crossFade())
                .into(target);

    }

    public void loadPicture(ImageView target, RequestListener<Drawable> listener, String path) {
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
                .listener(listener)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .transition(new DrawableTransitionOptions().crossFade())
                .into(target);

    }

    public void loadPictureRoundCornerss(ImageView target, String path, int roundingRadius, Transformation<Bitmap> transformation) {
        RequestOptions options = new RequestOptions()
                .transform(transformation, new RoundedCorners(roundingRadius));
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(target);
    }
    public void loadPictureRoundCornerss(ImageView target, String path, int roundingRadius) {
        RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(), new RoundedCorners(roundingRadius));
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(target);
    }

    public void loadPictureRoundCornerss(ImageView target, String path, int roundingRadius, @DrawableRes int defaultRes, int errorRes) {
        RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(), new RoundedCorners(roundingRadius))
                .placeholder(defaultRes)
                .error(errorRes);
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(target);
    }

    public void loadPictureCircleCrop(ImageView target, String path, @DrawableRes int defaultRes, int errorRes) {
        RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(), new CircleCrop())
                .placeholder(defaultRes)
                .error(errorRes);
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(target);
    }

    public void loadPictureCircleCrop(ImageView target, String path) {
        RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(), new CircleCrop())
                .placeholder(R.drawable.default_2)
                .error(R.drawable.default_1);
        Glide.with(mContext)
                .load(path)
                .skipMemoryCache(false)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(target);
    }

    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    public static void clearFileCache(Context context) {
        Glide.get(context).clearDiskCache();
    }
}
