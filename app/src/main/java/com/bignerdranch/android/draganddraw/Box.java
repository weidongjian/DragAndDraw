package com.bignerdranch.android.draganddraw;

import android.graphics.PointF;

public class Box {
	PointF mOrigin, mCurrent;
	
	public Box(PointF curr) {
		mOrigin = mCurrent = curr;
	}
	
	public void setCurrent(PointF current) {
		mCurrent = current;
	}
	
	public PointF getOrigin() {
		return mOrigin;
	}

}
