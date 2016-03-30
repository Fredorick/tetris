package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class Tetris extends SurfaceView implements IDrawable, IUpdateable, SurfaceHolder.Callback, OnTouchListener{
	
	Object[] objects = new Object[100];
	boolean touched = true;
	DisplayMetrics display;
	int side;
	int width;
	int height;
	Bitmap bitmap;
	Rect gameFieldrect;
	Rect scoreFieldrect;
	Rect nextFieldrect;
	Paint paint;
	GameField gameField;
	ScoreField scoreField;
	NextField nextField;
	
	int score;
	public Tetris(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		this.setOnTouchListener(this);
		side = 0;
		score = 0;
		paint = new Paint();		
		display = getResources().getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;
		int gamefieldwidth = (int)(width*0.75);
		int gamefieldheight = (int)(height*0.9);
		while(gamefieldwidth % 10 == 0){
			gamefieldwidth++;
		}
		while(gamefieldheight % 10 == 0){
			gamefieldheight++;
		}
		gameFieldrect = new Rect(20, 20, gamefieldwidth+20, gamefieldheight);
		gameField = new GameField(getResources(), gameFieldrect);
		scoreFieldrect = new Rect(gamefieldwidth+40,(width-gamefieldwidth), width-20, 550 );
		scoreField = new ScoreField(getResources(), scoreFieldrect);
		nextFieldrect = new Rect(gamefieldwidth+40, 20,width-20, (width-gamefieldwidth - 20));
		nextField = new NextField(getResources(), nextFieldrect);
		addObject(new Block(gamefieldwidth));
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	
		Motor motor = new Motor();
		motor.start();
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	class Motor extends Thread{
		SurfaceHolder holder = getHolder();
		public void run (){
			
			//paint.setAntiAlias(true);
			paint.setColor(Color.RED);//0xFFFF0000
			int x=0, y=0;
			while(true){
				Canvas canvas = holder.lockCanvas();
				if (canvas != null)
				{
					draw(canvas);
					holder.unlockCanvasAndPost(canvas);
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update(side);
				side = 0;
			}
		}
	}
	/*class Score extends Thread{
		public void run (){
			
			while(true){
				
			}
		}
	}*/
	public void draw(Canvas canvas)
	{	
		canvas.drawColor(Color.DKGRAY);
		gameField.draw(canvas);
		scoreField.draw(canvas);
		nextField.draw(canvas);
		for(int i = 99; i >= 0;i--){
			if (objects[i] != null && objects[i] instanceof IDrawable){
				((IDrawable)objects[i]).draw(canvas);
			}
		}
	}
	public void addObject(Object obj) {
		int i = 0;
		while (objects[i] != null) i++;
		objects[i] = obj;
	}

	@Override
	public void update(int x) {
		for(int i = 99; i >= 0;i--){
			if (objects[i] != null && objects[i] instanceof IUpdateable){
				((IUpdateable)objects[i]).update(x);
			}
		}
		scoreField.update(score);
		
	}
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(touched){
			if((event.getX()) >= width/2){
				side+=15;
			}
			else{
				side-=15;
			}
		}
		return false;
	}
	
}