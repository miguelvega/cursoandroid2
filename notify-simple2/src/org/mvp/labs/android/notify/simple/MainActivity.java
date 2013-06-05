package org.mvp.labs.android.notify.simple;

import com.example.notificationtutorial.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity implements OnClickListener {
	public static final int NOTIFICATION_ID1 = 1;
	public static final int NOTIFICATION_ID2 = 2;
	private NotificationManager mNotificationManager;
	private Button btn1, btn2;
	private boolean push1 = false, push2 = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(this);

	}

	// Create a Notification in Lastest Event layout.
	private void pushLastestEventNotification() {
		String ns = Context.NOTIFICATION_SERVICE;
		mNotificationManager = (NotificationManager) getSystemService(ns);
		int icon = R.drawable.icon;
		CharSequence tickerText = "Generar notificación";
		long when = System.currentTimeMillis();
		// Create new Notification objects
		Notification notification = new Notification(icon, tickerText, when);
		// Setting up for Notification fields.
		notification.flags = Notification.FLAG_AUTO_CANCEL;// Use to choice
															// action when
															// click
															// on
															// notification.
		notification.number += 1;
		// Create a pending intent to launch to another application when click
		// on notification.
		Context context = getApplicationContext();
		Intent notificationIntent = new Intent(this, ResultActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// Sets the contentView field to be a view with the standard
		// "Latest Event" layout.Uses the icon and when fields to set the icon
		// and time fields in the view.
		notification.setLatestEventInfo(context, "ContentTitle-9Android.net",
				"ContentText-Push Notification Tutorial", contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID1, notification);

	}

	// Create a Notification in Custom layout.
	private void pushCustomLayoutNotification() {
		String ns = Context.NOTIFICATION_SERVICE;
		mNotificationManager = (NotificationManager) getSystemService(ns);
		int icon = R.drawable.icon;
		CharSequence tickerText = "Mostrando Nueva Notificaión";
		long when = System.currentTimeMillis();
		// Create new Notification objects
		Notification notification = new Notification(icon, tickerText, when);
		// Setting up for Notification fields.
		notification.flags = Notification.FLAG_AUTO_CANCEL;// Use to choice
															// action when
															// click
															// on
															// notification.
		notification.number += 1;
		// Create a pending intent to launch to another application when click
		// on notification.
		Context context = getApplicationContext();
		Intent notificationIntent = new Intent(this, ResultActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		// Inflate custom layout to Remote View of Notification object.
		RemoteViews contentView = new RemoteViews(getPackageName(),
				R.layout.notification_layout);
		contentView.setImageViewResource(R.id.notification_layout_icon_img,
				R.drawable.icon);
		
		contentView.setTextViewText(R.id.notification_layout_text,
				"Notification Tutorial of 9Android.net");
		
		notification.contentView = contentView;
		notification.contentIntent = contentIntent;
		mNotificationManager.notify(NOTIFICATION_ID2, notification);

	}

	// -- Cancel Notification
	public void cancelNotification(int notificationId) {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		mNotificationManager.cancel(notificationId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			if (push1 == false) {
				pushLastestEventNotification();
				btn1.setText("Cancelar Notification");
				push1 = true;
			} else {
				cancelNotification(NOTIFICATION_ID1);
				btn1.setText("Lanzar la notificación");
				push1 = false;

			}
			break;
		case R.id.button2:
			if (push2 == false) {
				pushCustomLayoutNotification();
				btn2.setText("Cancelar Notification");
				push2 = true;
			} else {
				cancelNotification(NOTIFICATION_ID2);
				btn2.setText("Lanzar Notification con Layout propio");
				push2 = false;

			}
			break;
		}

	}
}
