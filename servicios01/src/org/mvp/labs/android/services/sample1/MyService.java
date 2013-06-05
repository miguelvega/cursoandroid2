package org.mvp.labs.android.services.sample1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Miguel Vega
 * @version $Id: MyService.java 0, 2013-05-26 7:19 PM mvega $
 */
public class MyService extends Service{
    static final String TAG = "Service1Tag";

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MyService ligado!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "MyService Creado!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "MyService Iniciado!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart");
        //Note: You can start a new thread and use it for long background processing from here.
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MyService Detenido!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");
    }
}