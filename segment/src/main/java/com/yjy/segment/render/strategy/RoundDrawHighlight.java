package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制圆角高亮
 */
public class RoundDrawHighlight extends DefaultDrawHighlight {
    private final float mRound;

    public RoundDrawHighlight(Configure configure, Frame frame, Strategy.DrawText drawTextStrategy) {
        super(configure, frame, drawTextStrategy);
        mRound = configure.getRound();
    }

    @Override
    protected void drawHighLightBar(Canvas canvas, Paint paint, Frame frame) {
        canvas.drawRoundRect(getFrame().getHighlightBound(), mRound, mRound, paint);
    }
}
