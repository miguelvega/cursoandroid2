package org.mvp.labs.android.services.sample2;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service{
	final static String MY_ACTION = "MY_ACTION";

	private String initData;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		this.initData = intent.getStringExtra("init_data");
		
		MyThread myThread = new MyThread();
		myThread.start();

		return super.onStartCommand(intent, flags, startId);
	}

	
	public class MyThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0; i<2; i++){
				try {
					Thread.sleep(5000);
					Intent intent = new Intent();
					intent.setAction(MY_ACTION);
					//aniadir data
					intent.putExtra("hora_envio", new SimpleDateFormat("HH:mm:ss").format(new Date()));
					intent.putExtra("init_data", initData);
					
					sendBroadcast(intent);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			stopSelf();
		}

	}
}
