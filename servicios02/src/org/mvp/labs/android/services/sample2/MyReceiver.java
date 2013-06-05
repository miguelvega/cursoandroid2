package org.mvp.labs.android.services.sample2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{

	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context,
				"Lanzado desde un Servicio!",
				Toast.LENGTH_LONG).show();

	}
}
