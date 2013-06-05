package org.mvp.labs.android.services.sample2;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service{
	MyServiceReceiver myServiceReceiver;

	protected final static String MY_ACTION = "MY_ACTION";
	protected final static String MY_ACTION_FROMACTIVITY = "MY_ACTION_FROMACTIVITY";

	public static final String CMD = "CMD";
	public static final int CMD_STOP = 1;
	boolean running;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		myServiceReceiver = new MyServiceReceiver();
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(MY_ACTION_FROMACTIVITY);
		registerReceiver(myServiceReceiver, intentFilter);
		running = true;

		MyThread myThread = new MyThread();
		myThread.start();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		this.unregisterReceiver(myServiceReceiver);
		super.onDestroy();
	}

	public class MyThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int i = 0;
			while(running){
				try {
					Thread.sleep(5000);
					Intent intent = new Intent();
					intent.setAction(MY_ACTION);

					intent.putExtra("iteration", i);
					intent.putExtra("hora_envio", new SimpleDateFormat("HH:mm:ss").format(new Date()));
					
					sendBroadcast(intent);

					i++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			stopSelf();
		}
	}

	public class MyServiceReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			int hostCmd = arg1.getIntExtra(CMD, 0);
			if(hostCmd == CMD_STOP){
				running = false;
				stopSelf();

				Toast.makeText(MyService.this,
						"El Servicio ha sido detenido por el Activity!",
						Toast.LENGTH_LONG).show();
			}
		}

	}

}
