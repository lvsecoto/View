package com.yjy.segment.render.strategy;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/12/16 0016.
 */

public interface Strategy {
    interface base {
        void prepare();
        void draw(Canvas canvas);
        Paint getPaint();
    }

    /**
     * Created by Administrator on 2017/12/16 0016.
     */

    interface DrawBackground extends base {
    }

    /**
     * Created by Administrator on 2017/12/16 0016.
     */

    interface DrawHighlight extends base {
    }

    /**
     * Created by Administrator on 2017/12/16 0016.
     */

    interface DrawText extends base {
        void drawText(Canvas canvas);
    }

    /**
     * Created by Administrator on 2017/12/16 0016.
     */

    interface DrawDivider extends base {
    }
}
