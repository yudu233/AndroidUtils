package com.coder.rain.library.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Describe :对话框帮助类
 * Created by Rain on 17-3-3.
 */
public class AlertDialogHelper {
    private static final String TAG = "dialogs";

    public static final void alert(Context context, String title, String word, String buttonText,
                                   final IAsk ask) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(word);

        builder.setTitle(title);

        builder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (ask != null)
                    ask.doIt();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public static final void alert(Context context, String title, String word, String buttonText) {
        alert(context, title, word, buttonText, null);
    }

    public static final void alert(Context context, String title, String word) {
        alert(context, title, word, "确定");
    }

    public interface IAsk {
        void doIt();
    }

    public interface IDismiss {
        void dismiss();
    }

    public static final void ask(final Context context, String title, String word, String buttonText) {
        ask(context, title, word, "取消", buttonText, null);
    }

    public static final void ask(final Context context, String title, String word, String negativeButtonText, String positiveButtonText, final IAsk iAsk) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(word);

        builder.setTitle(title);

        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (context instanceof IDismiss)
                    ((IDismiss) context).dismiss();
            }
        });

        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (iAsk != null)
                    iAsk.doIt();
                else if (context instanceof IAsk)
                    ((IAsk) context).doIt();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public static final void ask(final Context context, String title, String word) {
        ask(context, title, word, "确定");
    }

    public static final void show(final Context context, String title, String word,
                                  String positiveButtonText, String negativeButtonText, String neutralButtonText,
                                  DialogInterface.OnClickListener positiveCallback,
                                  DialogInterface.OnClickListener negativeCallback,
                                  DialogInterface.OnClickListener neutralCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(word);
        builder.setNegativeButton(positiveButtonText, positiveCallback);
        builder.setPositiveButton(negativeButtonText, negativeCallback);
        builder.setNeutralButton(neutralButtonText, neutralCallback);
        builder.setCancelable(false);
        builder.create().show();
    }
}
