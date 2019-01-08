package com.metacube.intermediatefirst.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.metacube.intermediatefirst.R;

public class MyCustomView extends View {

    private Paint mPaint;
    private Rect mRect;
    private int mSquareColor, mPadding = 0;

    public MyCustomView(Context context) {
        super(context);
        init(null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(@Nullable AttributeSet attributeSet) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();

        if (attributeSet == null) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable
                .MyCustomView);
        mSquareColor = typedArray.getColor(R.styleable.MyCustomView_square_color, Color.GREEN);
        mPaint.setColor(mSquareColor);
        typedArray.recycle();
    }

    public void swapColor() {
        mPaint.setColor(mPaint.getColor() == mSquareColor ? Color.RED : mSquareColor);
        postInvalidate();
    }

    public void customPaddingUp(int padding) {
        mPadding = mPadding + padding;
        postInvalidate();

    }

    public void customPaddingDown(int padding) {
        mPadding = mPadding - padding;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.left = 0 + mPadding;
        mRect.right = getWidth() - mPadding;
        mRect.top = 0 + mPadding;
        mRect.bottom = getHeight() - mPadding;
        canvas.drawRect(mRect, mPaint);
    }
}
