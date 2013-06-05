package org.mvp.labs.android.services.sample6;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * Este ejemplo, es un servicio que realiza la descarga de un archivo y se comunica con el componente que lo inicio
 * para informarle donde ha descargado el archivo.
 * @author miguel vega
 *
 */
public class MainActivity extends Activity {
	
	private Handler handler = new Handler() {
		public void handleMessage(Message message) {
			Object path = message.obj;
			if (message.arg1 == RESULT_OK && path != null) {
				Toast.makeText(MainActivity.this,
						"El archivo ha sido descargado en: " + path.toString(), Toast.LENGTH_LONG)
						.show();
			} else {
				Toast.makeText(MainActivity.this, "La descarga falló",
						Toast.LENGTH_LONG).show();
			}

		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void onClick(View view) {
		Intent intent = new Intent(this, DownloaderService.class);
		// Create a new Messenger for the communication back
		Messenger messenger = new Messenger(handler);
		intent.putExtra("MESSENGER", messenger);
		intent.setData(Uri.parse("https://github.com/miguelvega/cursoandroid/blob/master/README.md"));
		intent.putExtra("url", "https://github.com/miguelvega/cursoandroid/blob/master/README.md");
		startService(intent);
	}
}
