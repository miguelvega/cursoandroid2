package org.mvp.labs.android.services.aidl;

import java.text.SimpleDateFormat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * A service which exposes their behavior over an IPC
 * @author miguel vega
 *
 */
public class AIDLMessageService extends Service {
	private static final String APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE = "org.mvp.labs.action.TimeService";
	private final static String LOG_TAG = AIDLMessageService.class.getCanonicalName();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(LOG_TAG,"AIDLMessageService ha sido creado.");
	}

	@Override
	public void onDestroy() {
		Log.d(LOG_TAG,"AIDLMessageService ha sido destruido.");
		super.onDestroy();
	}


	@Override
	public IBinder onBind(Intent intent) {
		if(APPSOLUT_INTENT_ACTION_BIND_MESSAGE_SERVICE.equals(intent.getAction())) {
			Log.d(LOG_TAG,"AIDLMessageService ha sido ligado.");
			return new TimeMessageService(this);
		}
		return null;
	}

	/**
	 * Format the date to return
	 * @return
	 */
	String getStringForRemoteService() {
		return getString(R.string.time_message) + (new SimpleDateFormat(" hh:mm:ss").format(System.currentTimeMillis()));
	}

}
