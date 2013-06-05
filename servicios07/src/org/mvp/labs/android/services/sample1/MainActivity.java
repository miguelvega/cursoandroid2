package org.mvp.labs.android.services.sample1;
import org.mvp.labs.android.services.sample1.MyService.MyLocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Un servicio local para obtener la fecha del sistema
 * @author miguel
 *
 */
public class MainActivity extends Activity {
	MyService myService;
	boolean isBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection myConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder service) {
			MyLocalBinder binder = (MyLocalBinder) service;
			myService = binder.getService();
			isBound = true;
		}

		public void onServiceDisconnected(ComponentName arg0) {
			isBound = false;
		}

	};
	
	public void showTime(View view){
	   String currentTime = myService.getCurrentTime();
	   TextView myTextView = (TextView)findViewById(R.id.myTextView);
	   myTextView.setText(currentTime);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
