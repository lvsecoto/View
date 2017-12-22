package com.yjy.segment.frame;

import android.graphics.PointF;
import android.graphics.RectF;

import com.yjy.segment.render.Render;

public interface Frame {

    interface Item {

        RectF getBound();

        String getText();

        PointF getTextPos();

    }

    void measure(int widthMeasureSpec, int heightMeasureSpec);

    float getWidth();

    float getHeight();

    Item[] getItems();

    RectF getContentBound();

    RectF getHighlightBound();

    void setHighlightBound(RectF bound);

    RectF getBorderBound();
}
