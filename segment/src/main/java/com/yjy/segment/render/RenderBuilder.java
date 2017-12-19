package com.yjy.segment.render;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;
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

    public RenderBuilder(Configure configure, Frame frame) {
        mConfigure = configure;
        mFrame = frame;
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
        return new DefaultDrawText(mConfigure, mFrame);
    }

    private Strategy.DrawDivider getDrawDividerStrategy() {
        return new DefaultDrawDivider(mConfigure, mFrame);
    }

    private Strategy.DrawBackground getDrawBackgoundStrategy() {
        return new DefaultDrawBackground(mConfigure, mFrame);
    }

    private Strategy.DrawHighlight getDrawHighlightStrategy() {
        switch (mConfigure.getHighlightStyle()) {
            case ROUND:
                return new RoundDrawHighlight(mConfigure, mFrame, getDrawTextStrategy());
            default:
                return new DefaultDrawHighlight(mConfigure, mFrame, getDrawTextStrategy());
        }
    }

    public void setConfigure(Configure configure) {
        mConfigure = configure;
    }

    public void setFrame(Frame frame) {
        mFrame = frame;
    }
}
