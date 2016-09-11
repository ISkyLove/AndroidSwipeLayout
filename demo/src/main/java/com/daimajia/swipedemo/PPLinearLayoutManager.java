package com.daimajia.swipedemo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by lin on 9/10/16.
 */
public class PPLinearLayoutManager extends LinearLayoutManager {
    public PPLinearLayoutManager(Context context) {
        super(context);
    }

    public PPLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public PPLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        Log.i("linzhenhua", "canScrollVertically" + super.canScrollVertically());
        return super.canScrollVertically();
    }


}
