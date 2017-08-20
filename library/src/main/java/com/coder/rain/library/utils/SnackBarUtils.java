package com.coder.rain.library.utils;

import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Descriptions : SnackBar帮助类
 * GitHub : https://github.com/Rain0413
 * Blog   : http://blog.csdn.net/sinat_33680954
 * Created by Rain on 17-2-20.
 */
public class SnackBarUtils {

    //默认SnackBar字体颜色白色
    public static final int messageColor = 0xFFFFFF;
    public static final int actionColor = 0xFFFFFF;
    //默认SnackBar背景色为app主题颜色
    public static final int backgroundColor = 0x66B3FF;


    /**
     * 默认SnackBar字体颜色白色，背景颜色黑色
     * 短时间显示
     *
     * @param view
     * @param message
     * @return
     */
    public static Snackbar ShortSnackBar(View view, String message) {
        return ShortSnackBar(view, message, messageColor, backgroundColor);
    }

    public static Snackbar ShortSnackBar(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        setSnackBarColor(snackbar, messageColor, backgroundColor);
        snackbar.show();
        return snackbar;
    }

    /**
     * 长时间显示
     * 默认SnackBar字体颜色白色，背景颜色黑色
     *
     * @param view
     * @param message
     * @return
     */
    public static Snackbar LongSnackBar(View view, String message) {
        return LongSnackBar(view, message, messageColor, backgroundColor, actionColor);
    }

    public static Snackbar LongSnackBar(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        setSnackBarColor(snackbar, messageColor, backgroundColor);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar LongSnackBar(View view, String message, int messageColor, int backgroundColor, int actionColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        setSnackBarColor(snackbar, messageColor, backgroundColor, actionColor);
        snackbar.show();
        return snackbar;
    }

    /**
     * 自定义显示
     *
     * @param view
     * @param message
     * @return
     */
    public static Snackbar CustomizeSnackBar(View view, String message) {
        return CustomizeSnackBar(view, message, messageColor, backgroundColor, actionColor);
    }

    public static Snackbar CustomizeSnackBar(View view, String message, int messageColor, int backgroundColor, int actionColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        setSnackBarColor(snackbar, messageColor, backgroundColor, actionColor);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar topSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarView;
        View add_view = LayoutInflater.from(snackbarView.getContext()).inflate(null, null);//加载布局文件新建View

        return snackbar;
    }

    /**
     * 设置消息颜色，背景颜色，Action文字颜色
     *
     * @param snackBar
     * @param messageColor
     * @param backgroundColor
     * @param actionColor
     */
    public static void setSnackBarColor(Snackbar snackBar, int messageColor, int backgroundColor, int actionColor) {
        snackBar.setActionTextColor(actionColor);
        View view = snackBar.getView();
        if (view != null) {
            view.setBackgroundColor(backgroundColor);
            ((TextView) view.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(messageColor);
        }
    }

    /**
     * 设置消息颜色和背景颜色
     *
     * @param snackBar
     * @param messageColor
     * @param backgroundColor
     */
    public static void setSnackBarColor(Snackbar snackBar, int messageColor, int backgroundColor) {
        setSnackBarColor(snackBar, messageColor, backgroundColor, 0);
    }
}
