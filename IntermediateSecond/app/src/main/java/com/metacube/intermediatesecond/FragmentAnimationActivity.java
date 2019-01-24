package com.metacube.intermediatesecond;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentAnimationActivity extends AppCompatActivity implements FragmentManager
        .OnBackStackChangedListener {

    private MaterialButton slideInBtn, slideOutBtn, flipInBtn, flipOutBtn;
    private boolean mShowingBack, mShowingFront;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_animation);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, CardFrontFragment.getInstance())
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        init();
        methodListener();
    }

    private void init() {
        slideInBtn = findViewById(R.id.slideInBtn);
        slideOutBtn = findViewById(R.id.slideOutBtn);
        flipInBtn = findViewById(R.id.flipInBtn);
        flipOutBtn = findViewById(R.id.flipOutBtn);
    }

    private void methodListener() {
        slideInBtn.setOnClickListener(v -> slideInCard());
        slideOutBtn.setOnClickListener(v -> slideOutCard());
        flipInBtn.setOnClickListener(v -> flipInCard());
        flipOutBtn.setOnClickListener(v -> flipOutCard());
    }

    private void slideInCard() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.slide_left,
                        R.animator.slide_right)
                .replace(R.id.fragmentContainer, CardBackFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    private void slideOutCard() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.slide_left,
                        R.animator.slide_right)
                .replace(R.id.fragmentContainer, CardFrontFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    public void startAnimation(View view) {
        float dest;
        ImageView aniView = findViewById(R.id.imageView1);
        switch (view.getId()) {

            case R.id.Button01: {
                dest = 360;
                if (aniView.getRotation() == 360) {
                    dest = 0;
                }
                ObjectAnimator animation1 = ObjectAnimator.ofFloat(aniView,
                        "rotation", dest);
                animation1.setDuration(2000);
                animation1.start();
                break;
            }

            case R.id.Button02: {
                TextView aniTextView = findViewById(R.id.textView1);
                dest = 0;
                if (aniTextView.getX() <= 0) {
                    dest = 281;
                }
                ObjectAnimator animation2 = ObjectAnimator.ofFloat(aniTextView,
                        "x", dest);
                animation2.setDuration(2000);
                animation2.start();
                break;
            }

            case R.id.Button03: {
                dest = 1;
                if (aniView.getAlpha() > 0) {
                    dest = 0;
                }
                ObjectAnimator animation3 = ObjectAnimator.ofFloat(aniView,
                        "alpha", dest);
                animation3.setDuration(2000);
                animation3.start();
                break;
            }

            case R.id.Button04: {
                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(aniView, "alpha",
                        0f);
                fadeOut.setDuration(2000);
                ObjectAnimator mover = ObjectAnimator.ofFloat(aniView,
                        "translationX", -500f, 0f);
                mover.setDuration(2000);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha",
                        0f, 1f);
                fadeIn.setDuration(2000);
                AnimatorSet animatorSet = new AnimatorSet();

                animatorSet.play(mover).with(fadeIn).after(fadeOut);
                animatorSet.start();
                break;
            }
            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, HitActivity.class);
        startActivity(intent);
        return true;
    }

    private void flipInCard() {
        if (mShowingBack) {
            getSupportFragmentManager().popBackStack();
            return;
        }

        mShowingBack = true;

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragmentContainer, CardBackFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    private void flipOutCard() {
        if (mShowingFront) {
            getSupportFragmentManager().popBackStack();
            return;
        }

        mShowingFront = true;
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragmentContainer, CardFrontFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        mShowingFront = (getFragmentManager().getBackStackEntryCount() > 0);
    }
}
