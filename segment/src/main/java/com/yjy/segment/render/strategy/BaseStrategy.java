package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.animate.Animate;
import com.yjy.segment.frame.Frame;
import com.yjy.segment.render.strategy.utils.AnimateColor;

/**
 * 渲染策略类的基类
 * <p>
 * 它给派生类提供了以下方法用于{@link BaseStrategy#draw(Canvas) 绘制Canvas}
 * <ul>
 * <li>{@link BaseStrategy#getConfigure}</li>
 * <li>{@link BaseStrategy#getFrame()}</li>
 * <li>{@link BaseStrategy#getPaint()}</li>
 * </ul>
 * </p>
 */
class BaseStrategy implements Strategy.base, Animate.OnUpdateListener {

    private final Frame mFrame;

    private final Paint mPaint;

    private final Configure mConfigure;

    private final AnimateColor mAnimateColor;

    public BaseStrategy(Configure configure, Frame frame) {
        mConfigure = configure;
        mFrame = frame;
        mPaint = new Paint();
        mAnimateColor = new AnimateColor();
    }

    @Override
    public void prepare() {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void bindAnimate(Animate animate) {
        animate.addUpdateListener(this);
    }

    @Override
    public void onUpdate(float time) {
        mAnimateColor.onUpdate(time);
    }

    @Override
    public void onItemSelect(int selectedItem) {
        int[] itemColors = getConfigure().getItemColors();

        if (itemColors != null) {
            mAnimateColor.endColorWith(itemColors[selectedItem]);
        }
    }

    /**
     * @return 用于绘制的画笔
     */
    protected Paint getPaint() {
        return mPaint;
    }

    /**
     * @return 属性配置
     */
    protected Configure getConfigure() {
        return mConfigure;
    }

    /**
     * @return 要渲染的框架
     */
    protected Frame getFrame() {
        return mFrame;
    }

    protected int getMacroColor() {
        return mAnimateColor.getColor();
    }

}
