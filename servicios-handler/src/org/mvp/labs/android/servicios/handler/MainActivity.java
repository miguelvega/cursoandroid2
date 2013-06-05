package org.mvp.labs.android.servicios.handler;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressBar progress;
	private TextView text;


	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		text = (TextView) findViewById(R.id.textView1);

	}

	public void startProgress(View view) {
		// Do something long
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					final int value = i;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					progress.post(new Runnable() {
						@Override
						public void run() {
							text.setText("Cargando...");
							progress.setProgress(value);
						}
					});
				}
				//
				progress.post(new Runnable() {
					@Override
					public void run() {
						text.setText("El proceso ha finalizado");
						progress.setProgress(10);
					}
				});
			}
		};
		new Thread(runnable).start();
	}
}
