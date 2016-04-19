package com.example.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Block implements IDrawable, IUpdateable{
	int x;
	static int y = 200;
	static int size;
	Paint paint;
	Rect rectblock;
	boolean canGO = true;
	boolean canGOLeft;
	boolean canGORight;
	boolean canGOB;
	public Block (int size, int x){
		this.size = size;
		this.x = x;
		paint = new Paint();
		paint.setColor(Color.RED);		
		rectblock = new Rect(x, x, size+x, size+x);
	}
	public void draw(Canvas canvas){
		canvas.drawRect(x, y, size+x, size+y , paint);
		
	}
	@Override
	public void update(int side) {
		rectblock = new Rect(x, x, size+x, size+x);
			
		if(!(GameField.TryLeft(rectblock)))
			canGOLeft = false;
		else canGOLeft =  true;
		
		if(!(GameField.TryRight(rectblock)))
			canGORight = false;
		else canGORight =  true;
		
		if(GameField.TryBottom(rectblock))
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
}
