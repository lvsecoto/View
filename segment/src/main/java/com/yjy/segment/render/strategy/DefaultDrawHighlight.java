package com.yjy.segment.render.strategy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

/**
 * 绘制高亮区域
 */
public class DefaultDrawHighlight implements Strategy.DrawHighlight {

    public static final PorterDuffXfermode XFERMODE_DST_IN = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    private Configure mConfigure;

    private final Frame mFrame;

    private final Paint mPaint;

    private final Strategy.DrawText mDrawTextStrategy;

    private final float mRound;

    private ShapeDrawable mMasker;

    public DefaultDrawHighlight(Configure configure, Frame frame, Strategy.DrawText drawTextStrategy) {
        mConfigure = configure;

        mFrame = frame;
        mPaint = new Paint();
        mRound = configure.getRound();

        mPaint.setStyle(Paint.Style.FILL);

        mDrawTextStrategy = drawTextStrategy;
    }

    @Override
    public void prepare() {
        mMasker = new ShapeDrawable(new RoundRectShape(
                new float[]{mRound, mRound, mRound, mRound, mRound, mRound, mRound, mRound},
                null, null
        ));

        mMasker.getPaint().setColor(Color.BLACK);
        mMasker.getPaint().setStyle(Paint.Style.FILL);
        mMasker.getPaint().setXfermode(XFERMODE_DST_IN);

        mMasker.setBounds(
                (int) mFrame.getContentBound().left,
                (int) mFrame.getContentBound().top,
                (int) mFrame.getContentBound().right,
                (int) mFrame.getContentBound().bottom
        );

        mDrawTextStrategy.prepare();
    }

    @Override
    public void draw(Canvas canvas) {
        mDrawTextStrategy.getPaint().setColor(mConfigure.getHighlightTextColor());

        int sc = canvas.saveLayer(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight(),
                null,
                Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(mConfigure.getHighlightColor());
        drawHighLightBar(canvas);

        drawText(canvas);

        mMasker.draw(canvas);

        canvas.restoreToCount(sc);
    }

    private void drawText(Canvas canvas) {
        int sc = canvas.saveLayer(
                0,
                0,
                canvas.getWidth(),
                canvas.getHeight(),
                null,
                Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(Color.BLACK);
        drawHighLightBar(canvas);

        mDrawTextStrategy.getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mDrawTextStrategy.getPaint().setColor(Color.BLUE);
        mDrawTextStrategy.drawText(canvas);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        mPaint.setColor(mConfigure.getHighlightTextColor());
        drawHighLightBar(canvas);

        canvas.restoreToCount(sc);
    }

    protected void drawHighLightBar(Canvas canvas) {
        canvas.drawRect(mFrame.getHighlightBound(), mPaint);
    }

    @Override
    public Paint getPaint() {
        return mPaint;
    }

    protected Frame getFrame() {
        return mFrame;
    }
}
