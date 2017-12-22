package com.yjy.segment.animate;

/**
 * Created by Administrator on 2017/12/9 0009.
 */

public interface Animate {

    interface OnUpdateListener {

        void onUpdate(float time);

        void onItemSelect(int selectedItem);
    }

    void addUpdateListener(OnUpdateListener onUpdateListener);

    void initSelectItem(int selectedItem);

    void playSelectItem(int selectedItem);
}
