package org.mvp.labs.android.services.sample2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Crear un Servcio y Ademas un BoradcastReceiver, Detenerlo cuando querramos
 * @author miguel vega
 *
 */
public class MainActivity extends Activity {

	MyReceiver myReceiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnStop = (Button)findViewById(R.id.stop);
		btnStop.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(MyService.MY_ACTION_FROMACTIVITY);
				intent.putExtra(MyService.CMD, MyService.CMD_STOP);
				sendBroadcast(intent);
			}});
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
