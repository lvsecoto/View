package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class NoneDrawBackground implements Strategy.DrawBackground {
    private final Paint mPaint = new Paint();

    public NoneDrawBackground() {
    }

    @Override
    public void prepare() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public Paint getPaint() {
        return mPaint;
    }
}
