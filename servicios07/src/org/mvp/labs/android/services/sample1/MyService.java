package org.mvp.labs.android.services.sample1;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * @author Miguel Vega
 * @version $Id: MyService.java 0, 2013-05-26 7:19 PM mvega $
 */
public class MyService extends Service{
	private final IBinder myBinder = new MyLocalBinder();

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return myBinder;
	}
	
	public String getCurrentTime() {
		SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
		return (dateformat.format(new Date()));
	}

	public class MyLocalBinder extends Binder {
		MyService getService() {
			return MyService.this;
		}
	}
}