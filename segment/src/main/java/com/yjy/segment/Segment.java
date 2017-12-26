package com.yjy.segment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yjy.segment.animate.Animate;
import com.yjy.segment.animate.DefaultAnimate;
import com.yjy.segment.frame.DefaultFrame;
import com.yjy.segment.frame.Frame;
import com.yjy.segment.render.Render;
import com.yjy.segment.render.RenderBuilder;

public class Segment extends View {

    private int mSelectedItem;

    private Animate mAnimator = new DefaultAnimate();
    private Frame mFrame;
    private Render mRender;

    public Segment(Context context) {
        super(context);
    }

    public Segment(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Configure configure = new Configure(context, attrs);
        DefaultFrame defaultFrame = new DefaultFrame(configure);

        mFrame = defaultFrame;

        RenderBuilder renderBuilder = new RenderBuilder(configure, mFrame, mAnimator);
        mRender = renderBuilder.build();

        mAnimator.addUpdateListener(new Animate.OnUpdateListener() {
            @Override
            public void onUpdate(float time) {
                invalidate();
            }

            @Override
            public void onItemSelect(int selectedItem) {

            }
        });

        mAnimator.addUpdateListener(defaultFrame);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && onTouchSegment(event.getX(), event.getY())) {
            mAnimator.playSelectItem(mSelectedItem);
            return true;
        } else
            return false;
    }

    private boolean onTouchSegment(float x, float y) {
        int whichItemClick = findWhichItemClick(x, y, mFrame.getItems());

        if (whichItemClick == -1) {
            return false;
        }

        mSelectedItem = whichItemClick;

        return true;
    }

    private int findWhichItemClick(float x, float y, Frame.Item[] items) {
        for (int i = 0; i < items.length; i++) {
            RectF bound = items[i].getBound();


            if (bound.contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRender.draw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mFrame.measure(widthMeasureSpec, heightMeasureSpec);
        mRender.prepare();
        mAnimator.initSelectItem(mSelectedItem);

        setMeasuredDimension((int) mFrame.getWidth(), (int) mFrame.getHeight());
    }

}
