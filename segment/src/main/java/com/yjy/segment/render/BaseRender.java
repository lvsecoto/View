package com.yjy.segment.render;

import android.graphics.Canvas;

import com.yjy.segment.render.strategy.Strategy;

public class BaseRender implements Render {

    private Strategy.DrawText mDrawTextStrategy;

    private Strategy.DrawHighlight mDrawHighlightStrategy;

    private Strategy.DrawDivider mDrawDividerStrategy;

    private Strategy.DrawBackground mDrawBackgroundStrategy;

    @Override
    public void prepare() {
        mDrawBackgroundStrategy.prepare();
        mDrawTextStrategy.prepare();
        mDrawDividerStrategy.prepare();
        mDrawHighlightStrategy.prepare();
    }

    @Override
    public void draw(Canvas canvas) {
        mDrawBackgroundStrategy.draw(canvas);
        mDrawTextStrategy.draw(canvas);
        mDrawDividerStrategy.draw(canvas);
        mDrawHighlightStrategy.draw(canvas);
    }

    public void setDrawTextStrategy(Strategy.DrawText drawTextStrategy) {
        mDrawTextStrategy = drawTextStrategy;
    }

    public void setDrawHighlightStrategy(Strategy.DrawHighlight drawHighlightStrategy) {
        mDrawHighlightStrategy = drawHighlightStrategy;
    }

    public void setDrawDividerStrategy(Strategy.DrawDivider drawDividerStrategy) {
        mDrawDividerStrategy = drawDividerStrategy;
    }

    public void setDrawBackgroundStrategy(Strategy.DrawBackground drawBackgroundStrategy) {
        mDrawBackgroundStrategy = drawBackgroundStrategy;
    }

}
