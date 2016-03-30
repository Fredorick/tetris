package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class NextField implements IDrawable, IUpdateable {
	Bitmap bitmap;
	Paint paint;
	Rect rect;
	public NextField(Resources res, Rect rect) {
		paint = new Paint();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.fon2);
		this.rect = rect;
	}

	@Override
	public void update(int x) {
		
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, rect, paint);
	}

}
