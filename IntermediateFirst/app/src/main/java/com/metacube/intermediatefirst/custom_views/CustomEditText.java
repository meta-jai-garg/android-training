package com.metacube.intermediatefirst.custom_views;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.metacube.intermediatefirst.R;

public class CustomEditText extends AppCompatEditText {

    private Drawable mClearButtonImage;

    public CustomEditText(Context context) {
        super(context);
        init(null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    void init(@Nullable AttributeSet attrs) {
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable
                .ic_clear_opaque_24dp, null);
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
                } else {
                    mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable
                            .ic_clear_black_24dp, null);
                    setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                            mClearButtonImage, null);
                }
            }
        });

        setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= getRight() - getCompoundPaddingRight()) {
                    setText("");
                    return true;
                }
            }
            return false;
        });
    }
}
