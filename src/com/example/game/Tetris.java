package com.example.game;

import java.util.ArrayList;
import java.util.Stack;

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
	ArrayList<LifeBlock> blocks;
	Stack<LifeBlock> st;
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
	DeadBlock currentDeadBlock;
	int score;
	int count;
	public Tetris(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		this.setOnTouchListener(this);
		side = 0;
		score = 0;
		count = 0;
		paint = new Paint();
		blocks = new ArrayList<LifeBlock>();
		st = new Stack<LifeBlock>();
		display = getResources().getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;
		blocksize = (int)width/15;
		Fieldram =  (int)width/60;
		currentDeadBlock = new DeadBlock(null);
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
		addBlocks(new LifeBlock(blocksize,Fieldram));
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
					Thread.sleep(0);
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
		for(int i = 0; i < blocks.size();i++){
			if (blocks.get(i) != null && blocks.get(i) instanceof IDrawable){
				blocks.get(i).draw(canvas);
			}
			}
		//blocks.get(0).draw(canvas);
	}
	public void addBlocks(LifeBlock lifeBlock) {
			blocks.add(lifeBlock);
			if(st.isEmpty()){
			st.add(lifeBlock);}
			else{
			st.pop();
			st.add(lifeBlock);
			}
	}
	public DeadBlock Die(LifeBlock lifeBlock){
		DeadBlock db = new DeadBlock(lifeBlock);
		return db;
		
	}
	@Override
	public void update(int x) {
		/*for(int i = 0; i < 3;i++){                                          update
			if (blocks.get(i) != null){
				((IUpdateable)blocks.get(i)).update(x);
			}
		}*/
		st.peek().update(x);
		if((st.peek().CanGo() == false)){
		currentDeadBlock = Die(blocks.get(blocks.size()-1));
		blocks.set(blocks.size()-1, currentDeadBlock);
		addBlocks(new LifeBlock(blocksize,Fieldram));
		}
		score = (st.peek()).y;
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
