package org.mvp.labs.android.sensor.brujula;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements SensorListener {
	SensorManager sensorManager;
	static final int sensor = SensorManager.SENSOR_ORIENTATION;
	Brujula ImageCompass;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		ImageCompass = new Brujula(this);

		setContentView(ImageCompass);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(int sensor, int accuracy) {
	}

	public void onSensorChanged(int sensor, float[] values) {
		if (sensor != MainActivity.sensor)
			return;
		int orientation = (int) values[0];
		ImageCompass.setDirection(orientation);
	}
}