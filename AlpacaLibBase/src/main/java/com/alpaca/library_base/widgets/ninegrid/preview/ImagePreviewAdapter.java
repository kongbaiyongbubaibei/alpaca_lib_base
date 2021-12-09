package com.alpaca.library_base.widgets.ninegrid.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alpaca.library_base.R;
import com.alpaca.library_base.utils.ImageLoader;
import com.alpaca.library_base.widgets.PinchImageView;
import com.alpaca.library_base.widgets.ninegrid.ImageInfo;
import com.alpaca.library_base.widgets.ninegrid.NineGridView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/3/21
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class ImagePreviewAdapter extends PagerAdapter implements PhotoViewAttacher.OnPhotoTapListener {

    private List<ImageInfo> imageInfo;
    private Context context;
    private View currentView;

    public ImagePreviewAdapter(Context context, @NonNull List<ImageInfo> imageInfo) {
        super();
        this.imageInfo = imageInfo;
        this.context = context;
    }


    @Override
    public int getCount() {
        return imageInfo.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentView = (View) object;
    }

    @Override
    public int getItemPosition(@NonNull @NotNull Object object) {
//        if (imageInfo != null && imageInfo.size() == 0) {
        return POSITION_NONE;
//        } else {
//            return POSITION_UNCHANGED;
//        }
    }

    public View getPrimaryItem() {
        return currentView;
    }

    public ImageView getPrimaryImageView() {
        if (currentView != null) {
            return (ImageView) currentView.findViewById(R.id.mImageView);
        } else {
            return null;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photoview, container, false);
        final PinchImageView imageView = (PinchImageView) view.findViewById(R.id.mImageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ImageInfo info = this.imageInfo.get(position);
//        imageView.setOnPhotoTapListener(this);
        showExcessPic(info, imageView);
        imageView.setOnClickListener(v -> {
            if (context instanceof ImagePreviewActivity) {
                ((ImagePreviewActivity) context).finishActivityAnim();
            }
        });
        ImageLoader.with(view.getContext()).loadPictureAsBitmap(imageView, info.bigImageUrl);
        container.addView(view);
        return view;
    }

    /**
     * 展示过度图片
     */
    private void showExcessPic(ImageInfo imageInfo, PinchImageView imageView) {
        //先获取大图的缓存图片
        Bitmap cacheImage = NineGridView.getImageLoader().getCacheImage(imageInfo.bigImageUrl);
        //如果大图的缓存不存在,在获取小图的缓存
        if (cacheImage == null)
            cacheImage = NineGridView.getImageLoader().getCacheImage(imageInfo.thumbnailUrl);
        //如果没有任何缓存,使用默认图片,否者使用缓存
        if (cacheImage == null) {
            imageView.setImageResource(R.drawable.ic_default_color);
        } else {
            imageView.setImageBitmap(cacheImage);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 单击屏幕关闭
     */
    @Override
    public void onPhotoTap(View view, float x, float y) {
        ((ImagePreviewActivity) context).finishActivityAnim();
    }
}