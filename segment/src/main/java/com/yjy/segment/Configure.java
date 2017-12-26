package com.yjy.segment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by Administrator on 2017/12/12 0012.
 */
public class Configure {

    String[] mItemTexts;
    // Segment text size

    private final int[] mItemColors;

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

    private final float mDividerScale;

    private final int mTextColor;

    private final int mBorderColor;

    private final int mBackgroundColor;

    private final int mDividerColor;

    private final int mHighlightTextColor;

    private final int mHighlightColor;

    private  HighlightStyle mHighlightStyle;

    private final int mMacroColorBitFlags;

    Configure(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.segment);
        mRound = typedArray.getDimension(
                R.styleable.segment_round, dpToPix(context, 25));

        mDividerScale = getDividerScale(typedArray);

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

        mItemColors = getItemColors(context, typedArray);

        mMacroColorBitFlags = typedArray.getInt(R.styleable.segment_macroColor, 0);

        mTextColor = typedArray.getColor(R.styleable.segment_textColor, mColor);
        mHighlightTextColor = typedArray.getColor(R.styleable.segment_highlightTextColor, Color.WHITE);
        mBorderColor = typedArray.getColor(R.styleable.segment_borderColor, mColor);
        mDividerColor = typedArray.getColor(R.styleable.segment_dividerColor, mColor);
        mBackgroundColor = typedArray.getColor(R.styleable.segment_backgroundColor, 0);
        mHighlightColor = typedArray.getColor(R.styleable.segment_highlightColor, mColor);

        mHighlightStyle = HighlightStyle.values()[(typedArray.getInt(R.styleable.segment_highlightStyle, 0))];

    }

    @Nullable
    private int[] getItemColors(Context context, TypedArray typedArray) {
        int id = typedArray.getResourceId(R.styleable.segment_entryColor, 0);
        if (id == 0) {
            return null;
        }
        return context.getResources().getIntArray(id);
    }

    private float getDividerScale(TypedArray typedArray) {
        float dividerScale;
        dividerScale = typedArray.getFraction(R.styleable.segment_dividerScale, 1, 1, 1.0f);

        if (dividerScale > 1) {
            dividerScale = 1;
        } else if (dividerScale < 0) {
            dividerScale = 0;
        }
        return dividerScale;
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

    /**
     * 获取每个选项设定的颜色
     *
     * @return 当没有设置该项，返回null
     */
    @Nullable
    public int[] getItemColors() {
        return mItemColors;
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

    /**
     * @return 分割线高度百分比，范围0 - 1
     */
    public float getDividerScale() {
        return mDividerScale;
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

    /**
     * 判断是否绘制部分是否使用了macro color
     */
    public boolean isUseMacroColor(MacroColor macroColor) {
        return MacroColor.isMatch(mMacroColorBitFlags, macroColor);
    }

    public enum HighlightStyle {
        NORMAL, ROUND, CIRCLE
    }

    /**
     * 哪一个绘制部分需要用到Macro Color
     */
    public enum MacroColor {
        BORDER, BACKGROUND, DIVIDER, TEXT, HIGHLIGHT, HIGHLIGHT_TEXT;

        private final int mBitMasker = 1 << this.ordinal();

        public static boolean isMatch(int bitFlags, MacroColor macroColor) {
            return (bitFlags & macroColor.mBitMasker) == macroColor.mBitMasker;
        }
    }

}
