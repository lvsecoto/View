package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Segment;
import com.yjy.segment.frame.Frame;

/**
 * {@link com.yjy.segment.render.Render Render} 绘制策略接口
 */
public interface Strategy {

    interface base {
        /**
         * 当完成{@link Segment#onMeasure(int, int) }
         * {@link Frame#measure(int, int)}后会调用
         * <p>
         * <p>此时可以做一些和布局有关，和只需要执行一次，不需要在
         * {@link Segment#onDraw(Canvas)}频繁执行的操作</p>
         */
        void prepare();

        /**
         * 当Segment{@link Segment#onDraw(Canvas) 绘制}时会调用
         */
        void draw(Canvas canvas);

        /**
         * Paint对象
         */
        Paint getPaint();
    }

    /**
     * 背景绘制策略
     */
    interface DrawBackground extends base {
    }

    /**
     * 高亮绘制策略
     */
    interface DrawHighlight extends base {
    }

    /**
     * 文字绘制策略
     */
    interface DrawText extends base {

        /**
         * 绘制文字
         */
        void drawText(Canvas canvas);
    }

    /**
     * 分割线绘制策略
     */
    interface DrawDivider extends base {
    }
}
