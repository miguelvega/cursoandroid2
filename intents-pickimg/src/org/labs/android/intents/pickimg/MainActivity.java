package org.labs.android.intents.pickimg;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final int REQUEST_CODE = 12021;
	private Bitmap bitmap;
	private ImageView imageView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pickImage(View view){
    	Intent intent = new Intent();
    	intent.setType("image/*");
    	intent.setAction(Intent.ACTION_GET_CONTENT);
    	intent.addCategory(Intent.CATEGORY_OPENABLE);
    	
    	startActivityForResult(intent, REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	InputStream inputStream = null;
    	if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
    		if(bitmap!=null){
    			bitmap.recycle();
    		}
    		
    		try {
				inputStream = getContentResolver().openInputStream(data.getData());
				bitmap = BitmapFactory.decodeStream(inputStream);
				
				imageView.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		
    		
    	}
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
