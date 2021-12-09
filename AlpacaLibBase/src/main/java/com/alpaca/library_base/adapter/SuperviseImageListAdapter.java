package com.alpaca.library_base.adapter;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alpaca.library_base.R;
import com.alpaca.library_base.utils.DensityUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/10/12
 */
public class SuperviseImageListAdapter extends BaseQuickAdapter<Bitmap, BaseViewHolder> {

    public SuperviseImageListAdapter(List<Bitmap> data) {
        super(R.layout.item_supervise_tab_img, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Bitmap item) {
        ImageView iv_ad = helper.getView(R.id.iv_ad);

        //计算宽高比例
        double a = (double) item.getHeight() / (double) item.getWidth();
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (int) ((double) DensityUtil.getScreenWidth(mContext) * a)
        );
        iv_ad.setScaleType(ImageView.ScaleType.FIT_XY);
        iv_ad.setLayoutParams(params);
        iv_ad.setImageBitmap(item);

    }

}
