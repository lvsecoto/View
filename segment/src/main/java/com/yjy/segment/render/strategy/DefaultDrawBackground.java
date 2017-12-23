package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制背景
 * <p>
 * <p>背景是一个圆角矩形，包括边框和填充背景</p>
 * <p>边框颜色由{@link Configure#getBorderColor()}决定</p>
 * <p>填充背景颜色由{@link Configure#getBackgroundColor()}决定</p>
 * <b>若使用半透明颜色，注意边框和填充背景是重叠的</b>
 */
public class DefaultDrawBackground extends BaseStrategy implements Strategy.DrawBackground {

    public DefaultDrawBackground(Configure configure, Frame frame) {
        super(configure, frame);

        Paint paint = getPaint();
        paint.setStrokeWidth(configure.getBorderWidth());
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = getPaint();

        drawBackground(canvas, paint, getConfigure());
        drawBackgroundBorder(canvas, paint, getConfigure());
    }

    private void drawBackground(Canvas canvas, Paint paint, Configure configure) {
        float round = getConfigure().getRound();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(configure.getBackgroundColor());
        canvas.drawRoundRect(
                getFrame().getBorderBound(),
                round,
                round,
                paint
        );
    }

    private void drawBackgroundBorder(Canvas canvas, Paint paint, Configure configure) {
        float round = getConfigure().getRound();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(configure.getBorderColor());
        canvas.drawRoundRect(
                getFrame().getBorderBound(),
                round,
                round,
                paint
        );
    }
}
