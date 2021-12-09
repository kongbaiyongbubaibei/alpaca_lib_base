package com.alpaca.library_base.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alpaca.library_base.R;
import com.alpaca.library_base.utils.LogUtil;


public class DownloadProgressDialog extends Dialog {

    private static final String TAG = "DownloadProgressDialog";

    private ProgressBar pb_download_progress;

    private TextView tv_progress_value,tv_progress_title;

    public DownloadProgressDialog(Context context) {
        super(context, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_download_progress);
        pb_download_progress = (ProgressBar) findViewById(R.id.pb_download_progress);
        tv_progress_title = (TextView) findViewById(R.id.tv_progress_title);
        tv_progress_value = (TextView) findViewById(R.id.tv_progress_value);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        getWindow().setAttributes(params);
    }

    public void setDownloadProgress(int progress) {
        LogUtil.d(TAG, "progress:" + progress);
        if (progress < 0) progress = 0;
        if (progress > 100) progress = 100;
        pb_download_progress.setProgress(progress);
        tv_progress_value.setText("(" + progress + "%" + ")");
    }

    public void setText(String tx) {
        tv_progress_title.setText(tx);
    }

    public int getDownloadProgress() {
        return pb_download_progress.getProgress();
    }
}
