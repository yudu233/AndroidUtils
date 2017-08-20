package com.coder.rain.library.utils.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Describe:重写RadioButton的onDraw方法
 * 文字和自定义图片居中显示
 * Email:Bossrain99@163.com
 * Github:https://github.com/yudu233
 * Created by Rain on 2017/8/6 0006.
 */
public class DrawableCenterRadioButton extends RadioButton {

    private static final String TAG = "DrawableCenterRadioButton";

    public DrawableCenterRadioButton(Context context) {
        super(context);
    }

    public DrawableCenterRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean mIsSign = false;

    public boolean isSign() {
        return mIsSign;
    }

    public void setSign(boolean sign) {
        mIsSign = sign;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;

                // 绘制圆点
                if (mIsSign) {
                    canvas.translate(0, 0);
                    Paint p = new Paint();
                    p.setColor(0xfff43439);
                    p.setStrokeJoin(Paint.Join.ROUND);
                    p.setStrokeCap(Paint.Cap.ROUND);
                    p.setStrokeWidth(1);
                    p.setAntiAlias(true);
                    canvas.drawCircle((getWidth() - bodyWidth) / 2 + bodyWidth + 16, 32, 8, p);
                }
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
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
