package com.example.android_custom;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up main content view
        setContentView(R.layout.activity_main);
        //this button will show the dialog
        Button button1main = (Button) findViewById(R.id.Button01main);
 
        button1main.setOnClickListener(new OnClickListener() {
        @Override
            public void onClick(View v) {
                //set up dialog
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("El cuado de diálogo personalizado");
                dialog.setCancelable(true);
                //there are a lot of settings, for dialog, check them all out!
 
                //set up text
                TextView text = (TextView) dialog.findViewById(R.id.TextView01);
                text.setText(R.string.lots_of_text);
 
                
                //set up image view
                ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
                img.setImageResource(R.drawable.ic_launcher);
 
                //set up button
                Button button = (Button) dialog.findViewById(R.id.Button01);
                button.setOnClickListener(new OnClickListener() {
                @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                //now that the dialog is set up, it's time to show it    
                dialog.show();
            }
        });
    } 
}
