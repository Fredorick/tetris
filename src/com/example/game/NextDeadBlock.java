package com.example.game;

import android.graphics.Canvas;
import android.graphics.Rect;

public class NextDeadBlock extends DeadBlock {
	LifeBlock lifeBlock;
	Rect dedrect;
	@SuppressWarnings("static-access")
	public NextDeadBlock(LifeBlock lifeBlock) {	
		super(lifeBlock);
		lifeBlock = this.lifeBlock;
		dedrect = new Rect(lifeBlock.ReturnRect());
		dedrect.bottom +=size;
		dedrect.top +=size;
	}
	@Override
	public void draw(Canvas canvas){
		canvas.drawRect(dedrect, LifeBlock.ReturnPaint());
		
	}
}
