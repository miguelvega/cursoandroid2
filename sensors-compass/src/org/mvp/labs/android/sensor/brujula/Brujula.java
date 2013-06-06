package org.mvp.labs.android.sensor.brujula;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.Log;
import android.widget.ImageView;

public class Brujula extends ImageView {
	Paint mydPaint;
	int myddirection = 0;

	public Brujula(Context context) {
		super(context);

		mydPaint = new Paint();
		mydPaint.setColor(Color.WHITE);
		mydPaint.setStrokeWidth(2);
		mydPaint.setStyle(Style.STROKE);

		//this.setImageResource(R.drawable.compass);
	}

	@Override
	public void onDraw(Canvas canvas) {
		int height = this.getHeight();
		int width = this.getWidth();

		canvas.rotate(myddirection, width / 2, height / 2);

		Paint paint = new Paint();
		paint.setColor(0xffff0000);
		paint.setStyle(Paint.Style.FILL);

		int size = 100;
		
		Path path = new Path();
		path.moveTo(getWidth()/2-size, getHeight()/2+size);
		path.lineTo(getWidth()/2, getHeight()/2-size);
		path.lineTo(getWidth()/2+size, getHeight()/2+size);
		path.moveTo(getWidth()/2-size, getHeight()/2+size);
		
		Log.v("aaaaaaaaaaaaaaaa", getWidth()+", "+getHeight());
		
		canvas.drawPath(path, paint);

		super.onDraw(canvas);
	}

	public void setDirection(int direction) {
		this.myddirection = direction;
		this.invalidate();
	}

}