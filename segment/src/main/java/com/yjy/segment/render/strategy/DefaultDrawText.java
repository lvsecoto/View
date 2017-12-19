package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;
import com.yjy.segment.render.strategy.Strategy;

/**
 * Created by Administrator on 2017/12/16 0016.
 */
public class DefaultDrawText implements Strategy.DrawText {

    private final Configure mConfigure;

    private final Frame mFrame;

    private Paint mTextPaint;

    public DefaultDrawText(Configure configure, Frame frame) {
        mConfigure = configure;
        mFrame = frame;

        mTextPaint = new Paint();
        mTextPaint.setTextSize(configure.getTextSize());
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setFakeBoldText(true);
    }

    @Override
    public void prepare() {

    }

    @Override
    public void draw(Canvas canvas) {
        mTextPaint.setColor(mConfigure.getTextColor());
        drawText(canvas);
    }

    @Override
    public Paint getPaint() {
        return mTextPaint;
    }

    @Override
    public void drawText(Canvas canvas) {
        for (Frame.Item item : mFrame.getItems()) {
            canvas.drawText(
                    item.getText(), item.getTextPos().x, item.getTextPos().y, mTextPaint
            );
        }
    }
}
