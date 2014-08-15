package com.bignerdranch.android.draganddraw;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoxDragingView extends View {
	private Box mCurrentBox;
	private ArrayList<Box> mBox = new ArrayList<Box>();
	private Paint boxPaint;
	private Paint backgroundPaint;
	
	
	public BoxDragingView(Context context) {
		this(context, null);
	}
	
	public BoxDragingView(Context context, AttributeSet attr) {
		super(context, attr);
		boxPaint = new Paint();
		boxPaint.setColor(0x22ff0000);
		
		backgroundPaint = new Paint();
		backgroundPaint.setColor(0xfff8efe0);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPaint(backgroundPaint);
		
		for (Box box : mBox) {
			float left = Math.min(box.mCurrent.x, box.mOrigin.x);
			float right = Math.max(box.mCurrent.x, box.mOrigin.x);
			float top = Math.max(box.mCurrent.y, box.mOrigin.y);
			float bottom = Math.min(box.mCurrent.y, box.mOrigin.y);
			
			canvas.drawRect(left, top, right, bottom, boxPaint);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		PointF curr = new PointF(event.getX(), event.getY());
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mCurrentBox = new Box(curr);
			mBox.add(mCurrentBox);
			break;
			
		case MotionEvent.ACTION_MOVE:
			if (mCurrentBox != null) {
				mCurrentBox.setCurrent(curr);
				invalidate();
			}
			break;
			
		case MotionEvent.ACTION_UP:
			mCurrentBox = null;
			break;
			
		case MotionEvent.ACTION_CANCEL:
			mCurrentBox = null;
			break;

		}
		
		return true;
	}

}
