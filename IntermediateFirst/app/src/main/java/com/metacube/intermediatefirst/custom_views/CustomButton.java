package com.metacube.intermediatefirst.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.metacube.intermediatefirst.R;

public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        init(null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable
                .CustomButton);
        int buttonColor = typedArray.getColor(R.styleable.CustomButton_button_color, Color
                .TRANSPARENT);
        float cornerRadius = typedArray.getDimension(R.styleable.CustomButton_corner_radius, 0.0f);
        PaintDrawable paintDrawable = new PaintDrawable(buttonColor);
        paintDrawable.setCornerRadius(cornerRadius);
        setBackgroundDrawable(paintDrawable);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
