package util;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.game.R;

public class MyButton implements OnClickListener {
	Bitmap bitmap;
	Drawable drawable;
	Paint paint;
	Rect rect;
	Align align = Align.CENTER;
	String text;
	public MyButton(Resources res, String text) {
		bitmap = BitmapFactory.decodeResource(res, R.drawable.fon2);
		paint = new Paint();
		this.text = text;
	}
	public void draw(Canvas canvas, Rect rect){
	canvas.drawBitmap(bitmap, null, rect, paint);
	paint.setTextSize(rect.height()/4);
	paint.setTextAlign(align);
	canvas.drawText(text, rect.centerX(), (int)(rect.centerY()*1.1), paint);
	}
	@Override
	public void onClick(View v) {
	
	}
}
