package com.yjy.segment.frame;

import android.graphics.RectF;

import com.yjy.segment.Configure;
import com.yjy.segment.animate.Animate;

/**
 * Created by Administrator on 2017/12/11 0011.
 */

public class DefaultFrame extends BaseFrame implements Animate.OnUpdateListener {

    private RectF mStartHighlightBound = new RectF();
    private RectF mEndHighlightBound = new RectF();

    public DefaultFrame(Configure configure) {
        super(configure);
    }

    @Override
    public void onUpdate(float time) {
                setHighlightBound(new RectF(
                                getValByTime(mStartHighlightBound.left, mEndHighlightBound.left, time),
                                getValByTime(mStartHighlightBound.top, mEndHighlightBound.top, time),
                                getValByTime(mStartHighlightBound.right, mEndHighlightBound.right, time),
                                getValByTime(mStartHighlightBound.bottom, mEndHighlightBound.bottom, time)
                        )
                );
    }

    @Override
    public void onItemSelect(int selectedItem) {
        mStartHighlightBound = getHighlightBound();
        mEndHighlightBound = getItems()[selectedItem].getBound();
    }

    private float getValByTime(float start, float end, float time) {
        return start + (end - start) * time;
    }

}
