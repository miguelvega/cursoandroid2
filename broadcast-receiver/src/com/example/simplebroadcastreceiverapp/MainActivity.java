package com.example.simplebroadcastreceiverapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn;
	private static final String BROADCAST_INTENT_FILTER = "filtro.curso.android";
	private Intent broadcastIntent;
	// Create new BroadcastReceiver
	private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			showToast(intent.getExtras().get("akey").toString());
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		
		// registro dinámico BroadcastReceiver
		registerReceiver(myBroadcastReceiver, new IntentFilter(
				BROADCAST_INTENT_FILTER));
		
		broadcastIntent = new Intent(BROADCAST_INTENT_FILTER);
		broadcastIntent.putExtra("akey",
				"Ejemplo de BroadcastReceiver");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// Unregister BroadcastReceiver
		unregisterReceiver(myBroadcastReceiver);
	}

	private void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		sendBroadcast(broadcastIntent);
		
		
		//todo, despues de instalar broadcast propio descomentemos esto
		Intent intent = new Intent();
		intent.setAction("labs.android.CUSTOM_INTENT");
		sendBroadcast(intent);
		
	}
}
