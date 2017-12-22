package com.yjy.segment.animate;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class DefaultAnimate implements Animate {

    private List<OnUpdateListener> mOnUpdateListeners = new ArrayList<>();

    private ObjectAnimator mAnimator = ObjectAnimator.ofFloat(this, "Time", 0, 1);

    {
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                update();
            }
        });

        mAnimator.setDuration(250);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private float mTime;

    @Override
    public void addUpdateListener(OnUpdateListener onUpdateListener) {
        this.mOnUpdateListeners.add(onUpdateListener);
    }

    @Override
    public void initSelectItem(int selectedItem) {
        for (OnUpdateListener onUpdateListener : mOnUpdateListeners) {
            onUpdateListener.onItemSelect(selectedItem);
            mTime = 1.0f;
            onUpdateListener.onUpdate(mTime);
        }
    }

    @Override
    public void playSelectItem(int selectedItem) {
        if (mAnimator.isRunning()) {
            mAnimator.cancel();
        }

        for (OnUpdateListener onUpdateListener : mOnUpdateListeners) {
            onUpdateListener.onItemSelect(selectedItem);
        }

        mAnimator.start();
    }

    @SuppressWarnings("unused")
    public void setTime(float time) {
        this.mTime = time;
    }

    private void update() {
        for (OnUpdateListener onUpdateListener : mOnUpdateListeners) {
            onUpdateListener.onUpdate(mTime);
        }
    }
}
