package com.alpaca.library_base.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.alpaca.library_base.R;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/11/29
 */
public class ImagePickerUtils {

    public CoreConfig initImagePicker(Context context) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImagePickLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

        FunctionConfig functionConfig0 = new FunctionConfig.Builder()
                .setCropSquare(true)
                .setMutiSelectMaxSize(1)
                .setEnableCrop(true)
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setCropReplaceSource(true)
                .setForceCrop(true)
                .setEnableRotate(false)
                .setEnablePreview(false).build();
        ImageLoader imageLoader = new GlideImageLoader();
        return new CoreConfig.Builder(context, imageLoader, ThemeConfig.DEFAULT).setAnimation(0).setFunctionConfig(functionConfig0).build();
    }

    public CoreConfig initImagePicker(Context context,boolean isCrop) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImagePickLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(isCrop);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

        FunctionConfig functionConfig0 = new FunctionConfig.Builder()
                .setCropSquare(isCrop)
                .setMutiSelectMaxSize(1)
                .setEnableEdit(false)
                .setEnableCrop(false)
                .setEnableRotate(false)
                .setCropSquare(false)
                .setEnablePreview(false)
                .build();
        ImageLoader imageLoader = new GlideImageLoader();
        return new CoreConfig.Builder(context, imageLoader, ThemeConfig.DEFAULT).setAnimation(0).setFunctionConfig(functionConfig0).build();
    }

    private class GlideImagePickLoader implements com.lzy.imagepicker.loader.ImageLoader {

        @Override
        public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
            RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.default_hejinei).error(R.drawable.default_hejinei).diskCacheStrategy(DiskCacheStrategy.NONE).override(width, height).skipMemoryCache(true).centerCrop();
            Glide.with(activity)
                    .load(Uri.fromFile(new File(path)))
                    .apply(requestOptions)
                    .into(imageView);
        }

        @Override
        public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
            RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.default_hejinei).error(R.drawable.default_hejinei).diskCacheStrategy(DiskCacheStrategy.NONE).override(width, height).skipMemoryCache(true).centerCrop();
            Glide.with(activity)
                    .load(Uri.fromFile(new File(path)))
                    .apply(requestOptions)
                    .into(imageView);
        }

        @Override
        public void clearMemoryCache() {

        }
    }

    private class GlideImageLoader implements ImageLoader {

        @Override
        public void displayImage(Activity activity, String path, final GFImageView imageView, Drawable defaultDrawable, int width, int height) {
            RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.default_hejinei).error(R.drawable.default_hejinei).diskCacheStrategy(DiskCacheStrategy.NONE).override(width, height).skipMemoryCache(true).centerCrop();
            Glide.with(activity)
                    .load(Uri.fromFile(new File(path)))
                    .apply(requestOptions)
                    .into(imageView);
        }

        @Override
        public void clearMemoryCache() {
        }

    }
}
