package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制文字
 */
public class DefaultDrawText extends BaseStrategy implements Strategy.DrawText {

    public DefaultDrawText(Configure configure, Frame frame) {
        super(configure, frame);

        Paint paint = getPaint();
        paint.setTextSize(configure.getTextSize());
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setColor(getConfigure().getTextColor());
    }

    @Override
    public void draw(Canvas canvas) {
        drawText(canvas, getPaint(), getFrame());
    }

    private void drawText(Canvas canvas, Paint paint, Frame frame) {
        for (Frame.Item item : frame.getItems()) {
            canvas.drawText(
                    item.getText(), item.getTextPos().x, item.getTextPos().y, paint
            );
        }
    }

    @Override
    public void drawText(Canvas canvas) {
        drawText(canvas, getPaint(), getFrame());
    }
}
