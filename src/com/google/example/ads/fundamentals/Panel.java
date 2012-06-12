package com.google.example.ads.fundamentals;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

class Panel extends SurfaceView implements SurfaceHolder.Callback {
	CanvasThread canvasthread;
	private float mTouchX;
	private float mTouchY;
	
	mSoundManager.playSound(1);
	
    Bitmap kangoo = BitmapFactory.decodeResource(getResources(),R.drawable.kangoo);

	public Panel(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        // TODO Auto-generated constructor stub
	    getHolder().addCallback(this);
	    canvasthread = new CanvasThread(getHolder(), this);
	    setFocusable(true);
	}
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                    int height) {
            // TODO Auto-generated method stub
           
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
        canvasthread.setRunning(true);
        canvasthread.start();

           
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            boolean retry = true;
            canvasthread.setRunning(false);
            while (retry) {
                    try {
                            canvasthread.join();
                            retry = false;
                    } catch (InterruptedException e) {
                            // we will try it again and again...
                    }
            }

    }

    @Override
    public void onDraw(Canvas canvas) {
           
            Paint paint = new Paint();

            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(kangoo, mTouchX-kangoo.getWidth()/2, mTouchY-kangoo.getHeight()/2, null);
           
    }
    
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			mTouchX = event.getX();
			mTouchY = event.getY();
		}
		else
		{
			//mTouchX = -1;
			//mTouchY = -1;
		}
		super.onTouchEvent(event);
		return true;
	}
}