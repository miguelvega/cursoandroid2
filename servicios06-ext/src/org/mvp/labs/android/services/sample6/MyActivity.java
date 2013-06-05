package org.mvp.labs.android.services.sample6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupButton();
    }
    
    private void setupButton() {
        Button button = (Button) findViewById(R.id.notificationButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void createNotification() {
        new DownloadTask(getApplicationContext()).execute(0);
    }
}
