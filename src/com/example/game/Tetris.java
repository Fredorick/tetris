package com.example.game;

import util.MyButton;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class Tetris extends SurfaceView implements IDrawable, IUpdateable, SurfaceHolder.Callback, OnTouchListener


{
	Object[] objects = new Object[100];
	boolean touched = true;
	DisplayMetrics display;
	int side;
	int width;
	int height;
	int blocksize;
	int Fieldram;
	int gamefieldheight;
	int gamefieldwidth;
	Bitmap bitmap;
	Rect gameFieldrect;
	Rect scoreFieldrect;
	Rect nextFieldrect;	
	Rect buttonRect;
	Paint paint;
	GameField gameField;
	ScoreField scoreField;
	NextField nextField;
	MyButton menuButoon;
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
		blocksize = (int)width/15;
		Fieldram =  (int)width/60;
		gamefieldwidth = 10*blocksize;
		gamefieldheight = (int)(21*blocksize);
		gameFieldrect = new Rect(Fieldram, Fieldram, gamefieldwidth+Fieldram, gamefieldheight);
		gameField = new GameField(getResources(), gameFieldrect);
		nextFieldrect = new Rect(gamefieldwidth+2*Fieldram, Fieldram,width-Fieldram, (width-gamefieldwidth - Fieldram));
		nextField = new NextField(getResources(), nextFieldrect);
		scoreFieldrect = new Rect(gamefieldwidth+2*Fieldram,(width-gamefieldwidth), width-Fieldram, (width-gamefieldwidth + Fieldram + nextFieldrect.height()) );
		scoreField = new ScoreField(getResources(), scoreFieldrect);
		buttonRect = new Rect(3*Fieldram,gamefieldheight+Fieldram,width-3*Fieldram,height-Fieldram);
		menuButoon = new MyButton(getResources(), "Menu");
		addObject(new Block(blocksize,Fieldram));
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
			while(true){
				Canvas canvas = holder.lockCanvas();
				if (canvas != null)
				{
					ScoreField.SCupdate(score);
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
	public void draw(Canvas canvas)
	{	
		canvas.drawColor(Color.DKGRAY);
		gameField.draw(canvas);
		scoreField.draw(canvas);
		nextField.draw(canvas);
		menuButoon.draw(canvas,buttonRect);
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
		score = Block.y;
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
