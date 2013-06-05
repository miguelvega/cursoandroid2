package org.mvp.labs.android.services.alarm;

import java.util.concurrent.TimeUnit;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener{
	static final String BROADCAST = "org.mvp.broadcast.Target";
	
	
	PendingIntent pi;
	BroadcastReceiver br;
	AlarmManager am;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setup();
		findViewById(R.id.the_button).setOnClickListener(this);
	}
	
	private void setup() {
		br = new BroadcastReceiver() {
            @Override
			public void onReceive(Context c, Intent i) {
				Toast.makeText(c, "Se ha ejecutado el proceso programado!", Toast.LENGTH_LONG).show();
			}
        };
		registerReceiver(br, new IntentFilter(BROADCAST) );
		
        pi = PendingIntent.getBroadcast( this, 0, new Intent(BROADCAST), 0 );
        am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
	}

	@Override
	public void onClick(View v) {
		long time = TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS);
		
		am.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + time, pi );
	}
	
	@Override
	protected void onDestroy() {
		am.cancel(pi);
		unregisterReceiver(br);
		super.onDestroy();
	}
}
