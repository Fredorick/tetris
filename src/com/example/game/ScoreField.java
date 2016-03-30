package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;

public class ScoreField implements IDrawable, IUpdateable {
	String score;
	Bitmap bitmap;
	Paint paint;
	Rect rect;
	Align align = Align.CENTER;
	public ScoreField(Resources res, Rect rect) {
		score = "0";
		paint = new Paint();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.fon2);
		this.rect = rect;
	}

	@Override
	public void update(int score) {
		this.score = Integer.toString(score);
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, rect, paint);
		paint.setTextSize(rect.height()/2);
		paint.setTextAlign(align);
		canvas.drawText(score, rect.centerX(), (int)(rect.centerY()*1.1), paint);
		
	}

}
