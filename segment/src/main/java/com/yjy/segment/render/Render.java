package com.yjy.segment.render;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.yjy.segment.Configure;
import com.yjy.segment.frame.Frame;

public interface Render {
    void prepare();

    void draw(Canvas canvas);

}
