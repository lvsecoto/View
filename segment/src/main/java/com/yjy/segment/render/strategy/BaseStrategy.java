package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

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
class BaseStrategy implements Strategy.base {

    private final Frame mFrame;

    private final Paint mPaint;

    private final Configure mConfigure;

    public BaseStrategy(Configure configure, Frame frame) {
        mConfigure = configure;
        mFrame = frame;
        mPaint = new Paint();
    }

    @Override
    public void prepare() {

    }

    @Override
    public void draw(Canvas canvas) {

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

}
