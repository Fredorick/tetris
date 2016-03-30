package com.example.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block implements IDrawable, IUpdateable{
	static int x = 20;
	int y = 20;
	int size;
	Paint paint;
	public Block (int size){
		this.size = size/10;
		paint = new Paint();
		paint.setColor(Color.RED);
	}
	public void draw(Canvas canvas){
		canvas.drawRect(x, y, size+x, size+y , paint);
	}
	@Override
	public void update(int side) {
		//if(canMove())
		y++;
		if(side!=0){
			if(side>0) {x+=size;}
			else       {x-=size;}		
		}
	
	}
}
