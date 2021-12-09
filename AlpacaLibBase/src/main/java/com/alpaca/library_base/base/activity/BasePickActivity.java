package com.alpaca.library_base.base.activity;

import android.Manifest;

import com.alpaca.library_base.R;
import com.alpaca.library_base.base.presenter.BasePresenter;
import com.alpaca.library_base.base.view.IBaseView;
import com.alpaca.library_base.event.GetPickPhotosEvent;
import com.alpaca.library_base.utils.ImagePickerUtils;
import com.alpaca.library_base.utils.PermissionHelper;
import com.alpaca.library_base.widgets.SelectDialog;
import com.lzy.imagepicker.bean.ImageItem;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public abstract class BasePickActivity<T extends BasePresenter> extends BaseActivity<T> implements IBaseView {


    private List<ImageItem> iData = new ArrayList<>();
    private List<PhotoInfo> pData = new ArrayList<>();

    public void initSelectDiglog(List<String> names, SelectDialog.SelectDialogListener listener) {
        showDialog(listener, names);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener selectDialogListener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                selectDialogListener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    public void pickPhotos() {
        GalleryFinal.init(new ImagePickerUtils().initImagePicker(getContext(), false));
        GalleryFinal.openGalleryMuti(1, 9 - iData.size(), mOnHanlderResultCallback);
    }

    public void takePhotos() {
        PermissionHelper.runOnPermissionGranted(this, () -> {
            GalleryFinal.init(new ImagePickerUtils().initImagePicker(getContext(), false));
            GalleryFinal.openCamera(2, mOnHanlderResultCallback);
        }, () -> {
            showToast("拍照权限被拒绝，该功能无法正常使用");
        }, Manifest.permission.CAMERA);
    }

    GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            iData.clear();
            pData.addAll(resultList);
            if (resultList != null && resultList.size() > 0 && pData.size() != iData.size()) {
                for (int i = 0; i < pData.size(); i++) {
                    ImageItem imageItem = new ImageItem();
                    imageItem.height = pData.get(i).getHeight();
                    imageItem.width = pData.get(i).getWidth();
                    imageItem.path = pData.get(i).getPhotoPath();
                    iData.add(imageItem);
                }
            }
            EventBus.getDefault().post(new GetPickPhotosEvent(iData));
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            showToast("图片选取失败" + errorMsg);
        }
    };

    public List<ImageItem> getiData() {
        return iData;
    }

}
