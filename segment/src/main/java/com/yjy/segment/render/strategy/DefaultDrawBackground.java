package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制一个圆角边框
 */
public class DefaultDrawBackground implements Strategy.DrawBackground {

    private Configure mConfigure;
    private final Frame mFrame;

    private final float mRound;

    private Paint mPaint;


    public DefaultDrawBackground(Configure configure, Frame frame) {
        mConfigure = configure;

        mFrame = frame;
        mPaint = new Paint();
        mPaint.setStrokeWidth(configure.getBorderWidth());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        mRound = configure.getRound();
    }

    @Override
    public void prepare() {

    }

    @Override
    public void draw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mConfigure.getBackgroundColor());
        canvas.drawRoundRect(
                new RectF(mFrame.getBorderBound()),
                mRound,
                mRound,
                mPaint
        );

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mConfigure.getBorderColor());
        canvas.drawRoundRect(
                new RectF(mFrame.getBorderBound()),
                mRound,
                mRound,
                mPaint
        );
    }

    public Paint getPaint() {
        return mPaint;
    }
}
