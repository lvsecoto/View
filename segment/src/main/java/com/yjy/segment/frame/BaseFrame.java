package com.yjy.segment.frame;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;

import com.yjy.segment.Configure;

import static android.view.View.MeasureSpec;

public class BaseFrame implements Frame {

//    private Render mConfigure;

    private Item[] mItems;

    private Configure mConfigure;

    private RectF mContentBound;

    private RectF mBorderBound;

    private RectF mHighlightBound = new RectF();

    private Paint mTextPaint = new Paint();

    public BaseFrame(Configure configure) {
        mConfigure = configure;

        String[] itemTexts = mConfigure.getItemTexts();
        mItems = new Item[itemTexts.length];

        for (int i = 0; i < itemTexts.length; i++) {
            this.mItems[i] = new Item(itemTexts[i]);
        }
    }

    public static class Item implements Frame.Item {

        String text;

        PointF textPosF = new PointF();

        RectF boundF;

        Item(String text) {
            this.text = text;
        }

        @Override
        public RectF getBound() {
            return boundF;
        }

        @Override
        public String getText() {
            return text;
        }

        @Override
        public PointF getTextPos() {
            return textPosF;
        }
    }

    @Override
    public void measure(int widthMeasureSpec, int heightMeasureSpec) {
        mTextPaint.setTextSize(mConfigure.getTextSize());
        measureItems(widthMeasureSpec, heightMeasureSpec);
        measureView();
    }

    private void measureItems(int widthMeasureSpec, int heightMeasureSpec) {
        float itemHeight = getItemHeight(heightMeasureSpec);
        float actualInsetPaddingX = getActualInsetPaddingX(widthMeasureSpec, getTotalTextWidth());

        int posX = 0;
        float textHeight = (int) getTextHeight();
        for (Item item : mItems) {

            setItemBound(itemHeight, actualInsetPaddingX, posX, item);
            setItemTextPos(textHeight, item);

            posX += item.boundF.width();
        }
    }

    private void setItemBound(float itemHeight, float actualInsetPaddingX, int posX, Item item) {
        float textWidth = (int) getTextWidth(item.text);
        float itemWidth = textWidth + 2 * actualInsetPaddingX;

        item.boundF = new RectF(
                posX, 0, posX + itemWidth, itemHeight
        );

        item.boundF.offset(mConfigure.getBorderWidth() / 2, mConfigure.getBorderWidth() / 2);
    }

    private void setItemTextPos(float textHeight, Item item) {
        float textBottom = mTextPaint.getFontMetrics().bottom;
        item.textPosF.x = item.boundF.centerX();
        item.textPosF.y = item.boundF.centerY() + (textHeight / 2 - textBottom);
    }

    private float getItemHeight(int heightMeasureSpec) {
        float segmentHeight = 0;
        float textHeight = (int) (getTextHeight());
        switch (View.MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.UNSPECIFIED:
                segmentHeight = textHeight + 2 * mConfigure.getInsetPaddingY() - mConfigure.getBorderWidth() / 2;
                break;

            case MeasureSpec.EXACTLY:
                segmentHeight = MeasureSpec.getSize(heightMeasureSpec) - mConfigure.getBorderWidth() / 2;
                break;

            case MeasureSpec.AT_MOST:
                segmentHeight = Math.min(
                        textHeight + 2 * mConfigure.getInsetPaddingY() - mConfigure.getBorderWidth() / 2,
                        // size must not larger than this
                        MeasureSpec.getSize(heightMeasureSpec)
                );

                break;
        }
        return segmentHeight;
    }

    private float getTextWidth(String text) {
        return mTextPaint.measureText(text);
    }

    private float getTextHeight() {
        return mTextPaint.getFontMetrics().bottom - mTextPaint.getFontMetrics().top;
    }

    private float getActualInsetPaddingX(int widthMeasureSpec, int totalTextWidth) {
        float actualInsetPaddingX = 0;
        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.UNSPECIFIED:
                actualInsetPaddingX = mConfigure.getInsetPaddingX();
                break;

            case MeasureSpec.EXACTLY:
                actualInsetPaddingX = (MeasureSpec.getSize(widthMeasureSpec) - totalTextWidth -
                        mConfigure.getBorderWidth()) / mItems.length / 2;
                break;

            case MeasureSpec.AT_MOST:
                actualInsetPaddingX = Math.min(
                        mConfigure.getInsetPaddingX(),

                        // size must not larger than this
                        (MeasureSpec.getSize(widthMeasureSpec) - totalTextWidth -
                                mConfigure.getBorderWidth()) / mItems.length / 2
                );
                break;
        }
        return actualInsetPaddingX;
    }

    private int getTotalTextWidth() {
        int totalTextWidth = 0;
        for (Item item : mItems) {
            int textWidth = (int) getTextWidth(item.text);
            totalTextWidth += textWidth;
        }
        return totalTextWidth;
    }

    private void measureView() {
        mBorderBound = new RectF(
                mItems[0].boundF.left,
                mItems[0].boundF.top,
                mItems[mItems.length - 1].boundF.right,
                mItems[0].boundF.bottom
        );

        mContentBound = new RectF(mBorderBound);
        mContentBound.inset(-mConfigure.getBorderWidth() / 2, -mConfigure.getBorderWidth() / 2);
    }

    @Override
    public float getWidth() {
        return mContentBound.width();
    }

    @Override
    public float getHeight() {
        return mContentBound.height();
    }

    @Override
    public Item[] getItems() {
        return mItems;
    }

    @Override
    public RectF getContentBound() {
        return mContentBound;
    }

    @Override
    public RectF getHighlightBound() {
        return mHighlightBound;
    }

    @Override
    public void setHighlightBound(RectF bound) {
        mHighlightBound = bound;
    }

    @Override
    public RectF getBorderBound() {
        return mBorderBound;
    }

}
