package org.mvp.labs.android.broadcast.alarm;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAlert(View view){
    	EditText editText = (EditText) findViewById(R.id.time);
    	
    	int time = Integer.parseInt(editText.getText().toString());
    	
    	Intent intent = new Intent("alamrm.Broadcast");
    	
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 
    			1234, intent, 0);

    	AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
            + (time * 1000), pendingIntent);
        Toast.makeText(this, "Alarm programada en " + time + " segundos",
            Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
