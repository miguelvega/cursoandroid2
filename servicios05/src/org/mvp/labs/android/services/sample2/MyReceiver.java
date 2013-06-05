package org.mvp.labs.android.services.sample2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String horaEnvio = intent.getStringExtra("hora_envio");
		String desdeActivity = intent.getStringExtra("init_data");

		Toast.makeText(context,
				"Iteracion: "+intent.getIntExtra("iteration", -1)+", servicio envio a horas: "+horaEnvio,
				Toast.LENGTH_LONG).show();
	}

}
