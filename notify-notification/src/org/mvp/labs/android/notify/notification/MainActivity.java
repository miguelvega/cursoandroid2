package org.mvp.labs.android.notify.notification;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void createNotification(View view) {
		// Prepare intent which is triggered if the
		// notification is selected
		Intent intent = new Intent(this, NotificationActivity.class);
		
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		PendingIntent mapIntent = PendingIntent.getActivity(this, 0, 
				new Intent(Intent.ACTION_VIEW, Uri.parse("geo:50.123,7.1434?z=19")), 1);
		
		PendingIntent callIntent = PendingIntent.getActivity(this, 0, 
				new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+591)12345678")), 2);

		// Build notification
		// Actions are just fake
		Notification noti = new Notification.Builder(this)
		.setContentTitle("New mail from " + "test@gmail.com")
		.setContentText("Subject").setSmallIcon(R.drawable.icon)
		.setContentIntent(pIntent)
		.addAction(android.R.drawable.ic_dialog_dialer, "Llamar", callIntent)
		.addAction(android.R.drawable.ic_menu_mylocation, "Ubicacion", mapIntent)
		.addAction(R.drawable.icon, "Ver...", pIntent).build();
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Hide the notification after its selected
		noti.flags |= Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_SOUND;

		notificationManager.notify(0, noti);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
