package com.coder.rain.library.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.coder.rain.library.R;
import com.coder.rain.library.utils.weight.ShapeButton;


/**
 * Describe : 自定义吐司
 * Email:baossrain99@163.com
 * Created by Rain on 17-6-8.
 */
public class ToastUtils {

    private static final String TAG = "ToastUtils";

    private static Toast toast;
    private static ShapeButton mWord;

    public static void showToast(Context context, String word) {
        showToast(context, word, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String word, int duration) {
        if (toast == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.layout_toast_bg, null);
            mWord = (ShapeButton) view.findViewById(R.id.txv_text);
            toast = new Toast(context);
            toast.setDuration(duration);
            toast.setView(view);
        }
        mWord.setText(word);
        toast.show();
    }
}

/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */
