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

    private final String[] mItemTexts;

    private final float mTextSize;

    private final float mRound;

    private final float mBorderWidth;

    private final float mInsetPaddingX;

    private final float mInsetPaddingY;

    private final int mColor;

    private final int mBorderColor;

    private final int mBackgroundColor;

    private final int mDividerColor;

    private final int mTextColor;

    private final int mHighlightTextColor;

    private final int mHighlightColor;

    private final int[] mItemColors;

    private final int mMacroColorBitFlags;

    private final float mDividerScale;

    private  HighlightStyle mHighlightStyle;

    Configure(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.segment);

        mItemTexts = getItemTexts(context, typedArray);

        mTextSize = typedArray.getDimension(
                R.styleable.segment_textSize, dpToPix(context, 14));

        mRound = typedArray.getDimension(
                R.styleable.segment_round, dpToPix(context, 25));

        mBorderWidth = typedArray.getDimension(
                R.styleable.segment_borderWidth, dpToPix(context, 1.5f));

        mInsetPaddingX = typedArray.getDimension(
                R.styleable.segment_paddingX, dpToPix(context, 0)
        );

        mInsetPaddingY = typedArray.getDimension(
                R.styleable.segment_paddingY, dpToPix(context, 0)
        );

        mHighlightStyle = HighlightStyle.values()[(typedArray.getInt(R.styleable.segment_highlightStyle, 0))];

        mColor = typedArray.getColor(
                R.styleable.segment_color, Color.GRAY
        );

        mTextColor = typedArray.getColor(R.styleable.segment_textColor, mColor);
        mHighlightTextColor = typedArray.getColor(R.styleable.segment_highlightTextColor, Color.WHITE);
        mBorderColor = typedArray.getColor(R.styleable.segment_borderColor, mColor);
        mDividerColor = typedArray.getColor(R.styleable.segment_dividerColor, mColor);
        mBackgroundColor = typedArray.getColor(R.styleable.segment_backgroundColor, 0);
        mHighlightColor = typedArray.getColor(R.styleable.segment_highlightColor, mColor);

        mItemColors = getItemColors(context, typedArray);

        mMacroColorBitFlags = typedArray.getInt(R.styleable.segment_macroColor, 0);

        mDividerScale = getDividerScale(typedArray);

        typedArray.recycle();
    }

    private String[] getItemTexts(Context context, TypedArray typedArray) {
        int id = typedArray.getResourceId(R.styleable.segment_entry, -1);
        return context.getResources().getStringArray(id);
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

    public float getTextSize() {
        return mTextSize;
    }

    public float getRound() {
        return mRound;
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

    /**
     * 获取每个选项设定的颜色
     *
     * @return 当没有设置该项，返回null
     */
    @Nullable
    public int[] getItemColors() {
        return mItemColors;
    }

    public HighlightStyle getHighlightStyle() {
        return mHighlightStyle;
    }

    public int getColor() {
        return mColor;
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

    public int getHighlightColor() {
        return mHighlightColor;
    }

    public int getHighlightTextColor() {
        return mHighlightTextColor;
    }

    /**
     * 判断是否绘制部分是否使用了macro color
     */
    public boolean isUseMacroColor(MacroColor macroColor) {
        return MacroColor.isMatch(mMacroColorBitFlags, macroColor);
    }

    /**
     * @return 分割线高度百分比，范围0 - 1
     */
    public float getDividerScale() {
        return mDividerScale;
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
