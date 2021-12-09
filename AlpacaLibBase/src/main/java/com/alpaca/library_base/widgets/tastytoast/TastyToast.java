package com.alpaca.library_base.widgets.tastytoast;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alpaca.library_base.R;
import com.alpaca.library_base.constants.Constants;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 28/7/16.
 */
public class TastyToast {
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;


    public static final int SUCCESS = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
    public static final int INFO = 4;
    public static final int DEFAULT = 5;
    public static final int CONFUSING = 6;

    static SuccessToastView successToastView;
    static WarningToastView warningToastView;
    static ErrorToastView errorToastView;
    static InfoToastView infoToastView;
    static DefaultToastView defaultToastView;
    static ConfusingToastView confusingToastView;

    public static List<String> LIKE_LIST = new ArrayList<String>() {{
        add("点赞是一种动力");
        add("点赞是一种态度");
        add("你的点赞是最美好的期待");
        add("点赞攒人品");
        add("赞美之举，溢于言表");
    }};

    public static Toast makeText(Context context, String msg, int length, int type) {

        Toast toast = new Toast(context);


        switch (type) {
            case 1: {
                View layout = LayoutInflater.from(context).inflate(R.layout.success_toast_layout, null, false);
                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);
                successToastView = (SuccessToastView) layout.findViewById(R.id.successView);
                successToastView.startAnim();
                text.setBackgroundResource(R.drawable.success_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }
            case 2: {
                View layout = LayoutInflater.from(context).inflate(R.layout.warning_toast_layout, null, false);

                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);

                warningToastView = (WarningToastView) layout.findViewById(R.id.warningView);
                SpringSystem springSystem = SpringSystem.create();
                final Spring spring = springSystem.createSpring();
                spring.setCurrentValue(1.8);
                SpringConfig config = new SpringConfig(40, 5);
                spring.setSpringConfig(config);
                spring.addListener(new SimpleSpringListener() {

                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = (float) (0.9f - (value * 0.5f));

                        warningToastView.setScaleX(scale);
                        warningToastView.setScaleY(scale);
                    }
                });
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        spring.setEndValue(0.4f);
                    }
                });

                t.start();
                text.setBackgroundResource(R.drawable.warning_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }
            case 3: {
                View layout = LayoutInflater.from(context).inflate(R.layout.error_toast_layout, null, false);

                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);
                errorToastView = (ErrorToastView) layout.findViewById(R.id.errorView);
                errorToastView.startAnim();
                text.setBackgroundResource(R.drawable.error_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }
            case 4: {
                View layout = LayoutInflater.from(context).inflate(R.layout.info_toast_layout, null, false);

                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);
                infoToastView = (InfoToastView) layout.findViewById(R.id.infoView);
                infoToastView.startAnim();
                text.setBackgroundResource(R.drawable.info_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }
            case 5: {
                View layout = LayoutInflater.from(context).inflate(R.layout.default_toast_layout, null, false);

                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);
                defaultToastView = (DefaultToastView) layout.findViewById(R.id.defaultView);
                defaultToastView.startAnim();
                text.setBackgroundResource(R.drawable.default_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }
            case 6: {
                View layout = LayoutInflater.from(context).inflate(R.layout.confusing_toast_layout, null, false);

                TextView text = (TextView) layout.findViewById(R.id.toastMessage);
                text.setText(msg);
                confusingToastView = (ConfusingToastView) layout.findViewById(R.id.confusingView);
                confusingToastView.startAnim();
                text.setBackgroundResource(R.drawable.confusing_toast);
                text.setTextColor(Color.parseColor("#FFFFFF"));
                toast.setView(layout);
                break;
            }
        }
        toast.setDuration(length);
        toast.show();
        return toast;
    }

    public static void showLikeToast(Context context) {
        TastyToast.makeText(context, LIKE_LIST.get(Constants.getRadomNum(LIKE_LIST.size())), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
    }
    public static void showLikeToast(Context context,String content) {
        TastyToast.makeText(context, content, TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
    }
    public static void showWarnToast(Context context,String content) {
        TastyToast.makeText(context, content, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
    }

}
