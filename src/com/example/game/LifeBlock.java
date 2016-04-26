package com.example.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class LifeBlock extends Blocks implements IDrawable, IUpdateable, Moveable{
	static int x;
	static int y = 200;
	protected static int size;
	static Paint paint;
	static Rect rectblock;
	boolean canGO = true;
	boolean canGOLeft;
	boolean canGORight;
	boolean canGOB;
	final int red;
	final int green;
	final int blue;
	@SuppressWarnings("static-access")
	public LifeBlock (int size, int x){
		this.size = size;
		this.x = x+2*size;
		blue = (int) (Math.random()*255);
		green = (int) (Math.random()*255);
		red = (int) (Math.random()*255);
		paint = new Paint();
		paint.setColor(Color.rgb(green, red, blue));		
		rectblock = new Rect(x, y, size+x, size+y);
	}
	public void draw(Canvas canvas){
		canvas.drawRect(x, y, size+x, size+y , paint);
		
	}
	public boolean CanGo(){
		if(!canGOB)
		return false;
		else
		return true;
	}
	public static int ReturnSize(){
		return size;
	}
	public static Rect ReturnRect(){
		return rectblock;
	}
	public static Paint ReturnPaint(){
		return paint;
	}

	public void update(int side) {
		rectblock = new Rect(x, y, size+x, size+y);
			
		if(!(GameField.TryLeft(rectblock)))
			canGOLeft = false;
		else canGOLeft =  true;
		
		if(!(GameField.TryRight(rectblock)))
			canGORight = false;
		else canGORight =  true;
		
		if(GameField.TryBottom(rectblock, this))
			 canGOB =  true;		
		else canGOB = false;
		
		if(canGO){
			if(canGOB){
				y++;
					if(side!=0)
						if (side>0){  if(canGORight)  x+=size;   }
						else       {  if(canGOLeft)   x-=size;   }	
			}
		}
	}
	@Override
	public void moveable() {
		// TODO Auto-generated method stub
		
	}
}
