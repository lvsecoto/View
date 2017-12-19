package com.yjy.segment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by Administrator on 2017/12/12 0012.
 */
public class Configure {

    String[] mItemTexts;
    // Segment text size

    float mTextSize;
    // Stroke width of segment border
    float mBorderWidth;

    // Padding between text and segment border
    float mInsetPaddingX;

    // Padding between text and segment border
    float mInsetPaddingY;

    // default segment color
    int mColor;

    // roundBorder round r
    float mRound = 25;

    private final int mTextColor;

    private final int mBorderColor;

    private final int mBackgroundColor;

    private final int mDividerColor;

    private final int mHighlightTextColor;

    private final int mHighlightColor;

    private  HighlightStyle mHighlightStyle;

    Configure(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.segment);
        mRound = typedArray.getDimension(
                R.styleable.segment_round, dpToPix(context, 25));

        mBorderWidth = typedArray.getDimension(
                R.styleable.segment_borderWidth, dpToPix(context, 1.5f));

        mTextSize = typedArray.getDimension(
                R.styleable.segment_textSize, dpToPix(context, 14));

        mColor = typedArray.getColor(
                R.styleable.segment_color, Color.GRAY
        );

        mInsetPaddingX = typedArray.getDimension(
                R.styleable.segment_paddingX, dpToPix(context, 0)
        );

        mInsetPaddingY = typedArray.getDimension(
                R.styleable.segment_paddingY, dpToPix(context, 0)
        );

        int id = typedArray.getResourceId(R.styleable.segment_entry, -1);
        mItemTexts = context.getResources().getStringArray(id);

        mTextColor = typedArray.getColor(R.styleable.segment_textColor, mColor);
        mHighlightTextColor = typedArray.getColor(R.styleable.segment_highlightTextColor, Color.WHITE);
        mBorderColor = typedArray.getColor(R.styleable.segment_borderColor, mColor);
        mDividerColor = typedArray.getColor(R.styleable.segment_dividerColor, mColor);
        mBackgroundColor = typedArray.getColor(R.styleable.segment_backgroundColor, 0);
        mHighlightColor = typedArray.getColor(R.styleable.segment_highlightColor, mColor);

        mHighlightStyle = HighlightStyle.values()[(typedArray.getInt(R.styleable.segment_highlightStyle, 0))];

    }

    private int dpToPix(Context context, float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics());
    }

    public String[] getItemTexts() {
        return mItemTexts;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public float getBorderWidth() {
        return mBorderWidth;
    }

    public float getInsetPaddingX() {
        return mInsetPaddingX;
    }

    public float getInsetPaddingY() {
        return mInsetPaddingY;
    }

    public int getColor() {
        return mColor;
    }

    public float getRound() {
        return mRound;
    }

    public HighlightStyle getHighlightStyle() {
        return mHighlightStyle;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getHighlightTextColor() {
        return mHighlightTextColor;
    }

    public int getHighlightColor() {
        return mHighlightColor;
    }

    public enum HighlightStyle {
        NORMAL, ROUND
    }
}
