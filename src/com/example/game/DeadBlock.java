package com.example.game;

import android.graphics.Canvas;
import android.graphics.Rect;

public class DeadBlock extends LifeBlock implements IDrawable, Dead {
	Rect dedrect;
	LifeBlock lifeBlock;
	@SuppressWarnings("static-access")
	public DeadBlock(LifeBlock lifeBlock) {
		super(size, x);
		lifeBlock = this.lifeBlock;
		dedrect = new Rect(LifeBlock.ReturnRect());
	}
	@Override
	public void draw(Canvas canvas){
		canvas.drawRect(dedrect, LifeBlock.ReturnPaint());
		
	}
	
}
