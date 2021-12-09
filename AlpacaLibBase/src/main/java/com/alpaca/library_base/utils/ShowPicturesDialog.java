package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alpaca.library_base.widgets.ninegrid.ImageInfo;
import com.alpaca.library_base.widgets.ninegrid.preview.ImagePreviewActivity;

import java.io.Serializable;
import java.util.List;

/**
 * @文件名: ShowPicturesDialog
 * @功能描述:
 * @Date : 2020/10/22
 * @email:
 * @修改记录:
 */
public class ShowPicturesDialog {
    public static void showPictures(Context context, List<ImageInfo> imageInfo, int index) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, index);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void showPictures(Context context, ImageInfo imageInfo) {
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImagePreviewActivity.SINGLE_IMAGE_INFO, (Serializable) imageInfo);
        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, 0);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}
