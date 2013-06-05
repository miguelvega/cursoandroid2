package org.mvp.labs.android.services.sample2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{

	
	@Override
	public void onReceive(Context context, Intent intent) {
		String horaEnvio = intent.getStringExtra("hora_envio");
		
		Toast.makeText(context,
				"Lanzado desde un Servicio!, enviado en: "+horaEnvio,
				Toast.LENGTH_LONG).show();
	}
}
