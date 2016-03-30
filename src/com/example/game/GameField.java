package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameField implements IDrawable {
	Bitmap bitmap;
	Paint paint;
	Rect rect;
	public GameField(Resources res, Rect rect) {
		paint = new Paint();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.fon2);
		this.rect = rect;
	}
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, rect, paint);
		
	}

}
