package com.yjy.segment.render;

import com.yjy.segment.Configure;
import com.yjy.segment.animate.Animate;
import com.yjy.segment.frame.Frame;
import com.yjy.segment.render.strategy.CircleDrawHighLight;
import com.yjy.segment.render.strategy.DefaultDrawBackground;
import com.yjy.segment.render.strategy.DefaultDrawDivider;
import com.yjy.segment.render.strategy.DefaultDrawHighlight;
import com.yjy.segment.render.strategy.DefaultDrawText;
import com.yjy.segment.render.strategy.RoundDrawHighlight;
import com.yjy.segment.render.strategy.Strategy;

/**
 * Created by Administrator on 2017/12/16 0016.
 */

public class RenderBuilder {

    private Configure mConfigure;
    private Frame mFrame;
    private Animate mAnimate;

    public RenderBuilder(Configure configure, Frame frame, Animate animate) {
        mConfigure = configure;
        mFrame = frame;
        mAnimate = animate;
    }

    public Render build() {
        BaseRender baseRender = new BaseRender();

        baseRender.setDrawTextStrategy(getDrawTextStrategy());
        baseRender.setDrawDividerStrategy(getDrawDividerStrategy());
        baseRender.setDrawHighlightStrategy(getDrawHighlightStrategy());
        baseRender.setDrawBackgroundStrategy(getDrawBackgoundStrategy());

        return baseRender;

    }

    private Strategy.DrawText getDrawTextStrategy() {
        Strategy.DrawText strategy = new DefaultDrawText(mConfigure, mFrame);
        strategy.bindAnimate(mAnimate);

        return strategy;
    }

    private Strategy.DrawDivider getDrawDividerStrategy() {
        Strategy.DrawDivider strategy = new DefaultDrawDivider(mConfigure, mFrame);
        strategy.bindAnimate(mAnimate);

        return strategy;
    }

    private Strategy.DrawBackground getDrawBackgoundStrategy() {
        Strategy.DrawBackground strategy = new DefaultDrawBackground(mConfigure, mFrame);
        strategy.bindAnimate(mAnimate);

        return strategy;
    }

    private Strategy.DrawHighlight getDrawHighlightStrategy() {
        Strategy.DrawHighlight strategy;
        switch (mConfigure.getHighlightStyle()) {
            case ROUND:
                strategy = new RoundDrawHighlight(mConfigure, mFrame, getDrawTextStrategy());
                break;
            case CIRCLE:
                strategy = new CircleDrawHighLight(mConfigure, mFrame, getDrawTextStrategy());
                break;
            default:
                strategy = new DefaultDrawHighlight(mConfigure, mFrame, getDrawTextStrategy());
                break;
        }
        strategy.bindAnimate(mAnimate);

        return strategy;
    }

    public void setConfigure(Configure configure) {
        mConfigure = configure;
    }

    public void setFrame(Frame frame) {
        mFrame = frame;
    }
}
