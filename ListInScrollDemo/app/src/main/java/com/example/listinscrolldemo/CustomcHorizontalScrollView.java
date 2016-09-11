package com.example.listinscrolldemo;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * 
 * @author LXM
 * 
 * @date 2015-3-4
 * 
 * @detail :自定义HorizontalScrollView，实现多级联动
 */
public class CustomcHorizontalScrollView extends HorizontalScrollView {

	private Context context;
	private ArrayList<View> mViews = new ArrayList<View>();
	private int poi;
	private SendCurrentX sendCurrentX;

	public CustomcHorizontalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public CustomcHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mViews != null) {
			for (int i = 0; i < mViews.size(); i++) {
				if (i != poi) {
					mViews.get(i).scrollTo(l, t);
					int scrollX = mViews.get(i).getScrollX();
					sendCurrentX.currentX(scrollX, t);
				}

			}
		}
	}

	public void setScrollView(ArrayList<View> views, int poi,
			SendCurrentX sendCurrentX) {
		mViews = views;
		this.poi = poi;
		this.sendCurrentX = sendCurrentX;
	}

	public void setXScroll(int scrollX, int t) {
//		Log.i("滑动的位置--》》", scrollX + "");
		if (mViews != null) {
			for (int i = 0; i < mViews.size(); i++) {
				if (scrollX >= 0 && scrollX <= 240) {
					if ((scrollX > (240 - scrollX))) {
						mViews.get(i).scrollTo(240, t);
					} else {
						mViews.get(i).scrollTo(0, t);
					}
				} else if (scrollX > 240 && scrollX <= 480) {
					if (((scrollX - 240) > (480 - scrollX))) {
						mViews.get(i).scrollTo(480, t);
					} else {
						mViews.get(i).scrollTo(240, t);
					}
				} else if (scrollX > 480 && scrollX <= 720) {
					if (((scrollX - 480) > (720 - scrollX))) {
						mViews.get(i).scrollTo(720, t);
					} else {
						mViews.get(i).scrollTo(480, t);
					}
				}
			}
//			Log.i("滑动结束后--》》", mViews.get(0).getScrollX() + "");
			sendCurrentX.currentX(mViews.get(0).getScrollX(), t);

		}
	}

	public interface SendCurrentX {
		public void currentX(int currentX, int t);
	}
}
