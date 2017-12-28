package com.yjy.segment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * 这个类用于保存用户对Segment属性设置
 */
public class Configure {

    private static final int DEFAULT_TEXT_SIZE = 14;

    private static final int DEFAULT_ROUND = 8;

    private static final float DEFAULT_BORDER_WIDTH = 1.5f;

    private static final int DEFAULT_PADDING_X = 20;

    private static final int DEFAULT_PADDING_Y = 4;

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

    /**
     * 此方法在View的构造器中调用
     *
     * @param context View的Context
     * @param attrs   View的attrs
     */
    Configure(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.segment);

        mItemTexts = getItemTexts(context, typedArray);

        mTextSize = typedArray.getDimension(
                R.styleable.segment_textSize, dpToPix(context, DEFAULT_TEXT_SIZE));

        mRound = typedArray.getDimension(
                R.styleable.segment_round, dpToPix(context, DEFAULT_ROUND));

        mBorderWidth = typedArray.getDimension(
                R.styleable.segment_borderWidth, dpToPix(context, DEFAULT_BORDER_WIDTH));

        mInsetPaddingX = typedArray.getDimension(
                R.styleable.segment_paddingX, dpToPix(context, DEFAULT_PADDING_X)
        );

        mInsetPaddingY = typedArray.getDimension(
                R.styleable.segment_paddingY, dpToPix(context, DEFAULT_PADDING_Y)
        );

        mHighlightStyle = HighlightStyle.values()[(typedArray.getInt(R.styleable.segment_highlightStyle, 0))];

        mColor = typedArray.getColor(
                R.styleable.segment_color, getPrimaryColor(context)
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

    private int getPrimaryColor(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
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

    /**
     * @return 所有条目的文本
     */
    public String[] getItemTexts() {
        return mItemTexts;
    }

    /**
     * @return 文本字体大小(px)，默认为{@value #DEFAULT_TEXT_SIZE}(sp)
     */
    public float getTextSize() {
        return mTextSize;
    }

    /**
     * @return 圆角大小(px)，默认为{@value #DEFAULT_ROUND}(dp)
     */
    public float getRound() {
        return mRound;
    }

    /**
     * @return 边框线条宽度(px)，(px)默认为{@value #DEFAULT_BORDER_WIDTH}(dp)
     */
    public float getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * @return X方向上边框的内空白大小(px)，默认为{@value #DEFAULT_PADDING_X}
     */
    public float getInsetPaddingX() {
        return mInsetPaddingX;
    }

    /**
     * @return Y方向上边框的内空白大小(px)，默认为{@value #DEFAULT_PADDING_Y}
     */
    public float getInsetPaddingY() {
        return mInsetPaddingY;
    }

    /**
     * 获取所有条目设置的颜色
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

    /**
     * @return 获取颜色，默认为{@link R.attr#colorPrimary Primary Color}
     */
    public int getColor() {
        return mColor;
    }

    /**
     * @return 获取边框颜色，默认取值{@link #getColor()}
     */
    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * @return 获取背景颜色，默认无颜色#0000
     */
    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    /**
     * @return 获取分割线颜色，默认取值{@link #getColor()}
     */
    public int getDividerColor() {
        return mDividerColor;
    }

    /**
     * @return 获取文本颜色，默认取值{@link #getColor()}
     */
    public int getTextColor() {
        return mTextColor;
    }

    /**
     * @return 获取高亮颜色，默认取值{@link #getColor()}
     */
    public int getHighlightColor() {
        return mHighlightColor;
    }

    /**
     * @return 获取高亮文本颜色，默认取值{@link Color#WHITE}
     */
    public int getHighlightTextColor() {
        return mHighlightTextColor;
    }

    /**
     * 判断哪些绘制部分使用了macro color
     * @param macroColor 要判断的绘制部分
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

    /**
     * 高粱类型
     */
    public enum HighlightStyle {

        /**
         * 矩形
         */
        NORMAL,

        /**
         * 圆角矩形
         */
        ROUND,

        /**
         * 圆形
         */
        CIRCLE

    }

    /**
     * 哪一个绘制部分需要用到Macro Color
     */
    public enum MacroColor {

        /**
         * 背景边框
         */
        BORDER,

        /**
         * 背景
         */
        BACKGROUND,

        /**
         * 分割线
         */
        DIVIDER,

        /**
         * 文本
         */
        TEXT,

        /**
         * 高亮
         */
        HIGHLIGHT,

        /**
         * 高亮文本
         */
        HIGHLIGHT_TEXT;

        private final int mBitMasker = 1 << this.ordinal();

        public static boolean isMatch(int bitFlags, MacroColor macroColor) {
            return (bitFlags & macroColor.mBitMasker) == macroColor.mBitMasker;
        }
    }

}
