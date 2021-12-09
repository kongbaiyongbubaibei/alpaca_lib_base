package com.alpaca.library_base.utils;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.widget.TextView;

/**
 * @文件名: TextColorUtil
 * @功能描述:
 * @Date : 2021/6/22
 * @email:
 * @修改记录:
 */
public class TextColorUtil {

    private static String TAG = "TextColorUtil";

    /**
     * 设置textview 的颜色渐变
     *
     * @param text
     */

    public static void setTextViewStyles(TextView text, int startColor, int centerColor, int endColor) {
        if (text == null) {
            return;
        }
        text.getPaint().setShader(null);
        int[] colors = {startColor, centerColor, endColor};
        float[] position = {0f, 0.5f, 1.0f};
        float textWidth = text.getPaint().measureText(text.getText().toString());
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textWidth, text.getPaint().getTextSize(), colors,
                position,
                Shader.TileMode.REPEAT);
        text.getPaint().setShader(mLinearGradient);
        text.postInvalidateDelayed(50);

    }

}
