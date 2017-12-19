package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class GreyDrawBackground implements Strategy.DrawBackground {
    private DefaultDrawBackground mDrawBorder;

    public GreyDrawBackground(Configure configure, Frame frame) {
        mDrawBorder = new DefaultDrawBackground(
                configure,
                frame
        );
        mDrawBorder.getPaint().setColor(
                Color.argb((int) (255 * 0.14), 0, 0, 0)
        );
    }

    @Override
    public void prepare() {

    }

    @Override
    public void draw(Canvas canvas) {
        mDrawBorder.draw(canvas);
    }

    @Override
    public Paint getPaint() {
        return mDrawBorder.getPaint();
    }
}
