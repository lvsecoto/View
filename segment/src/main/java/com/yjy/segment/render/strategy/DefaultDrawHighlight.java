package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制高亮区域
 *
 * <p>带有一个遮罩，去除超出Segment显示区域部分</p>
 */
public class DefaultDrawHighlight extends BaseStrategy implements Strategy.DrawHighlight {

    private static final PorterDuffXfermode XFERMODE_DST_OUT = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    private static final PorterDuffXfermode XFERMODE_DST_IN = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    private static final PorterDuffXfermode XFERMODE_SRC_OUT = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);

    private final TextPaintStrategy mTextPaintStrategy = new TextPaintStrategy();

    private final Strategy.DrawText mDrawTextStrategy;

    private final float mRound;

    private ShapeDrawable mMaskerDrawable;

    public DefaultDrawHighlight(Configure configure, Frame frame, Strategy.DrawText drawTextStrategy) {
        super(configure, frame);

        Paint paint = getPaint();
        paint.setStyle(Paint.Style.FILL);

        mRound = getConfigure().getRound();

        mDrawTextStrategy = drawTextStrategy;

        mMaskerDrawable = new ShapeDrawable();
        mMaskerDrawable.getPaint().setColor(Color.BLACK);
        mMaskerDrawable.getPaint().setStyle(Paint.Style.FILL);
        mMaskerDrawable.getPaint().setXfermode(XFERMODE_DST_IN);

    }

    @Override
    public void prepare() {
        createMasker(mMaskerDrawable, getFrame());

        mDrawTextStrategy.prepare();
    }

    private void createMasker(ShapeDrawable maskerDrawable, Frame frame) {
        Shape shape = new RoundRectShape(
                new float[]{mRound, mRound, mRound, mRound, mRound, mRound, mRound, mRound},
                null, null
        );

        maskerDrawable.setShape(shape);
        maskerDrawable.setBounds(
                (int) frame.getContentBound().left,
                (int) frame.getContentBound().top,
                (int) frame.getContentBound().right,
                (int) frame.getContentBound().bottom
        );
    }

    @Override
    public void draw(Canvas canvas) {

        int sc = canvas.saveLayer(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight(),
                null,
                Canvas.ALL_SAVE_FLAG);

        drawHighLightBar(canvas);
        drawText(canvas);
        mMaskerDrawable.draw(canvas);

        canvas.restoreToCount(sc);
    }

    private void drawHighLightBar(Canvas canvas) {
        Paint paint = getPaint();
        getPaint().setColor(getConfigure().getHighlightColor());

        drawHighLightBar(canvas, paint, getFrame());
    }

    protected void drawHighLightBar(Canvas canvas, Paint paint, Frame frame) {
        canvas.drawRect(frame.getHighlightBound(), paint);
    }

    private void drawText(Canvas canvas) {
        int sc = canvas.saveLayer(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight(),
                null,
                Canvas.ALL_SAVE_FLAG);

        drawTextMasker(canvas, mDrawTextStrategy, getPaint());
        drawTextOnMasker(canvas, getPaint());

        canvas.restoreToCount(sc);
    }

    /**
     * 在遮罩上黑色文字转换为高亮颜色
     */
    private void drawTextOnMasker(Canvas canvas, Paint paint) {
        paint.setColor(getConfigure().getHighlightTextColor());
        paint.setXfermode(XFERMODE_SRC_OUT);
        drawHighLightBar(canvas, paint, getFrame());
        paint.setXfermode(null);
    }

    /**
     * 绘制一个在高亮区域内的黑色文字遮罩
     */
    private void drawTextMasker(Canvas canvas, Strategy.DrawText drawTextStrategy, Paint paint) {
        paint.setColor(Color.BLACK);
        drawHighLightBar(canvas, paint, getFrame());

        drawTextStrategy.drawText(canvas, mTextPaintStrategy);
    }

    private static class TextPaintStrategy implements Strategy.DrawText.PaintStrategy {

        private Xfermode mCachedXfermode;
        private int mCachedColor;

        @Override
        public void changePaint(Paint textPaint) {

            mCachedColor = textPaint.getColor();
            textPaint.setColor(Color.BLACK);

            mCachedXfermode = textPaint.getXfermode();
            textPaint.setXfermode(XFERMODE_DST_OUT);
        }

        @Override
        public void restorePaint(Paint textPaint) {
            textPaint.setColor(mCachedColor);
            textPaint.setXfermode(mCachedXfermode);
        }
    }

}
