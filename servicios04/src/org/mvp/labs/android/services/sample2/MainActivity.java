package org.mvp.labs.android.services.sample2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.Toast;


/**
 * Crear un Servcio y Ademas un BoradcastReceiver
 * @author miguel
 *
 */
public class MainActivity extends Activity {

	MyReceiver myReceiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub

		//Register BroadcastReceiver
		//to receive event from our service
		myReceiver = new MyReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(MyService.MY_ACTION);
		registerReceiver(myReceiver, intentFilter);

		//Start our own service
		Intent intent = new Intent(this, MyService.class);
		intent.putExtra("init_data", "desde Activity al Service en startService");
		startService(intent);

		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		unregisterReceiver(myReceiver);
		Toast.makeText(this, "Se ha detenido manualmente la Actividad, desinstalando el receiver", Toast.LENGTH_LONG);
		super.onStop();
	}
}
