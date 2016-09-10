package com.daimajia.swipedemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lin on 9/10/16.
 */
public class PPRecycleView extends RecyclerView {

    public interface onPPInterceptTouchEvent {
        boolean onPPInterceptTouchEvent(MotionEvent e);
    }

    private onPPInterceptTouchEvent mPPInterceptTouchEventListen;

    public void setonPPInterceptTouchEvent(onPPInterceptTouchEvent listen) {
        mPPInterceptTouchEventListen = listen;
    }

    public PPRecycleView(Context context) {
        super(context);
    }

    public PPRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PPRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (mPPInterceptTouchEventListen != null) {
            boolean isIntercept = mPPInterceptTouchEventListen.onPPInterceptTouchEvent(e);
            if (isIntercept) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(e);
    }
}
