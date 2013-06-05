package org.mvp.labs.android.broadcast.propio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Intent Detectado.", Toast.LENGTH_LONG).show();
	}

}
