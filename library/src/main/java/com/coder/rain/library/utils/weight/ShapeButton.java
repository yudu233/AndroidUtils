package com.coder.rain.library.utils.weight;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;

import com.coder.rain.library.R;


/**
 * Describe :
 * Email:baossrain99@163.com
 * Created by Rain on 17-5-25.
 */
public class ShapeButton extends android.support.v7.widget.AppCompatTextView {

    private static final String TAG = "ShapeButton";

    private float radius;
    private float stroke;
    private ColorStateList strokeColor;
    private ColorStateList buttonColor;
    private int color_strokeColor;

    public ShapeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShapeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    void init(Context context, AttributeSet attrs) {
        setClickable(true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeButton);
        radius = typedArray.getDimension(R.styleable.ShapeButton_radius, 0);
        stroke = typedArray.getDimension(R.styleable.ShapeButton_stroke, 0);
        strokeColor = typedArray.getColorStateList(R.styleable.ShapeButton_strokeColor);
        buttonColor = typedArray.getColorStateList(R.styleable.ShapeButton_buttonColor);
        GradientDrawable drawable = getDrawable();
        if (Build.VERSION.SDK_INT < 16) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
        setGravity(Gravity.CENTER);
    }

    public GradientDrawable getDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);

        int color_normal = buttonColor.getColorForState(new int[]{android.R.attr.state_enabled}, 0);
        if (strokeColor != null)
            color_strokeColor = strokeColor.getColorForState(new int[]{android.R.attr.state_enabled}, 0);
        gradientDrawable.setColor(color_normal);//设置颜色
        if (stroke != 0) gradientDrawable.setStroke(dp2px(stroke), color_strokeColor);//描边
        gradientDrawable.setCornerRadius(dp2px(radius));//设置圆角的半径
        return gradientDrawable;
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
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
