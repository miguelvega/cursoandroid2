package org.mvp.labs.android.media.videplayer;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		VideoView view = (VideoView) findViewById(R.id.videoView);

		MediaController mediaController= new MediaController(this);
		mediaController.setAnchorView(view);        

		File extStore = Environment.getExternalStorageDirectory();
		
		Uri uri=Uri.parse(extStore.getAbsolutePath()+"/small.mp4"); //write your location here       
		view.setMediaController(mediaController);
		view.setVideoURI(uri);        
		view.requestFocus();
		view.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
