package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制分割线
 */
public class DefaultDrawDivider implements Strategy.DrawDivider {

    private Configure mConfigure;

    private final Frame mFrame;

    private final Paint mPaint;

    public DefaultDrawDivider(Configure configure, Frame frame) {
        mConfigure = configure;
        mFrame = frame;

        mPaint = new Paint();
        mPaint.setStrokeWidth(configure.getBorderWidth());
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    public void prepare() {

    }

    @Override
    public void draw(Canvas canvas) {
        mPaint.setColor(mConfigure.getDividerColor());
        for (int i = 0; i < mFrame.getItems().length - 1; i++) {
            RectF itemBound = mFrame.getItems()[i].getBound();

            canvas.drawLine(
                    itemBound.right,
                    itemBound.top,
                    itemBound.right,
                    itemBound.bottom,
                    mPaint
            );
        }
    }

    @Override
    public Paint getPaint() {
        return mPaint;
    }
}
