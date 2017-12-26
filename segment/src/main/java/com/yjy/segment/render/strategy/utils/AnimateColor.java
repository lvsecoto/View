package com.yjy.segment.render.strategy.utils;

import android.graphics.Color;

/**
 * 动画颜色
 */
public class AnimateColor {

    private int mStartColor = 0;

    private int mThisColor = 0;

    private int mEndColor = 0;

    /**
     * 设定要变化到哪个颜色
     */
    public void endColorWith(int color) {
        mEndColor = color;
        mStartColor = mThisColor;
    }

    /**
     * 获取当前颜色
     */
    public int getColor() {
        return mThisColor;
    }

    /**
     * 时间更新后调用此函数
     */
    public void onUpdate(float time) {
        mThisColor = getColorByTime(mStartColor, mEndColor, time);
    }

    private int getColorByTime(int startColor, int endColor, float time) {
        return Color.argb(
                getIntByTime(Color.alpha(startColor), Color.alpha(endColor), time),
                getIntByTime(Color.red(startColor), Color.red(endColor), time),
                getIntByTime(Color.green(startColor), Color.green(endColor), time),
                getIntByTime(Color.blue(startColor), Color.blue(endColor), time)
        );
    }

    private int getIntByTime(int startVal, int endVal, float time) {
        return (int) (startVal + (endVal - startVal) * time);
    }

}
