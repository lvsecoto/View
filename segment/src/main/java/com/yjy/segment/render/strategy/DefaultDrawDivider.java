package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制给每一个item之间绘制分割线
 */
public class DefaultDrawDivider extends BaseStrategy implements Strategy.DrawDivider {

    private boolean mIsUseMacroColor;

    public DefaultDrawDivider(Configure configure, Frame frame) {
        super(configure, frame);

        Paint paint = getPaint();
        paint.setStrokeWidth(configure.getBorderWidth());
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(configure.getDividerColor());

        mIsUseMacroColor = getConfigure().isUseMacroColor(Configure.MacroColor.DIVIDER);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = getPaint();

        paint.setColor(getDividerColor());
        for (int i = 0; i < getFrame().getItems().length - 1; i++) {
            RectF itemBound = getFrame().getItems()[i].getBound();

            drawDivider(canvas, paint, itemBound);
        }
    }

    private int getDividerColor() {
        if (mIsUseMacroColor) {
            return getMacroColor();
        }
        return getConfigure().getDividerColor();
    }

    private void drawDivider(Canvas canvas, Paint paint, RectF itemBound) {
        canvas.drawLine(
                itemBound.right,
                itemBound.top,
                itemBound.right,
                itemBound.bottom,
                paint
        );
    }
}
