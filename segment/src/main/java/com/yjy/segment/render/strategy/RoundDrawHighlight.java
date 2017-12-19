package com.yjy.segment.render.strategy;

import android.graphics.Canvas;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class RoundDrawHighlight extends DefaultDrawHighlight {
    private final float mRound;

    public RoundDrawHighlight(Configure configure, Frame frame, Strategy.DrawText drawTextStrategy) {
        super(configure, frame, drawTextStrategy);
        mRound = configure.getRound();
    }

    @Override
    protected void drawHighLightBar(Canvas canvas) {
        canvas.drawRoundRect(getFrame().getHighlightBound(), mRound, mRound, getPaint());
    }
}
