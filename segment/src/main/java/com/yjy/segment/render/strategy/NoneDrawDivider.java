package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class NoneDrawDivider implements Strategy.DrawDivider {
    private final Paint mPaint = new Paint();
    public NoneDrawDivider(Configure configure, Frame frame) {
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
