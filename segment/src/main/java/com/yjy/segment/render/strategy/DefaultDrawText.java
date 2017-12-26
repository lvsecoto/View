package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制文字
 */
public class DefaultDrawText extends BaseStrategy implements Strategy.DrawText {

    private final boolean mIsUseMacroColor;

    public DefaultDrawText(Configure configure, Frame frame) {
        super(configure, frame);

        Paint paint = getPaint();
        paint.setTextSize(configure.getTextSize());
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);

        mIsUseMacroColor = getConfigure().isUseMacroColor(Configure.MacroColor.TEXT);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = getPaint();
        paint.setColor(getTextColor());

        drawText(canvas, paint, getFrame());
    }

    private int getTextColor() {
        if (mIsUseMacroColor) {
            return getMacroColor();
        }
        return getConfigure().getTextColor();
    }

    private void drawText(Canvas canvas, Paint paint, Frame frame) {
        for (Frame.Item item : frame.getItems()) {
            canvas.drawText(
                    item.getText(), item.getTextPos().x, item.getTextPos().y, paint
            );
        }
    }

    @Override
    public void drawText(Canvas canvas, PaintStrategy paintStrategy) {
        Paint paint = getPaint();
        paintStrategy.changePaint(paint);
        drawText(canvas, paint, getFrame());
        paintStrategy.restorePaint(paint);
    }
}
