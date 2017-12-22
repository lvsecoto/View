package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制圆形高亮区域，直径为高亮区域的高度
 */

public class CircleDrawHighLight extends DefaultDrawHighlight {

    public CircleDrawHighLight(Configure mConfigure, Frame mFrame, Strategy.DrawText drawTextStrategy) {
        super(mConfigure, mFrame, drawTextStrategy);
    }

    @Override
    protected void drawHighLightBar(Canvas canvas) {
        RectF hlBound = getFrame().getHighlightBound();
        float r = hlBound.height() / 2;

        canvas.drawCircle(hlBound.centerX(), hlBound.centerY(), r, getPaint());
    }
}
