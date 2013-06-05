package org.mvp.labs.android.notify.simple;

import com.example.notificationtutorial.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ResultActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }
}
