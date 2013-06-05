package org.mvp.labs.android.services.sample6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

public class DownloadTask extends AsyncTask<Integer, Integer, Void>{
    private NotificationHelper mNotificationHelper;
    public DownloadTask(Context context){
        mNotificationHelper = new NotificationHelper(context);
    }

    protected void onPreExecute(){
        //Create the notification in the statusbar
        mNotificationHelper.createNotification();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        //This is where we would do the actual download stuff
        // publishing progress every second
        Uri data = Uri.parse("http://robtowns.com/music/blind_willie.mp3");
		String urlPath = "http://robtowns.com/music/blind_willie.mp3";
		String fileName = data.getLastPathSegment();
		
		File output = new File(Environment.getExternalStorageDirectory(), fileName);
		if (output.exists()) {
			output.delete();
		}

		InputStream stream = null;
		FileOutputStream fos = null;
		try {

			URL url = new URL(urlPath);
			
			URLConnection conn = url.openConnection();
			

			int max = conn.getContentLength();
			
			stream = conn.getInputStream();
			
			fos = new FileOutputStream(output.getPath());
			
			int read = -1;
			byte[] buffer = new byte[1024];
			
			int downloaded = 0;
			
			while ((read = stream.read(buffer)) != -1) {
				fos.write(read);
				
				downloaded+=buffer.length;
				
				publishProgress((int)(100d*(double)downloaded/(double)max));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return null;
    }
    protected void onProgressUpdate(Integer... progress) {
        //This method runs on the UI thread, it receives progress updates
        //from the background thread and publishes them to the status bar
        mNotificationHelper.progressUpdate(progress[0]);
    }
    protected void onPostExecute(Void result)    {
        //The task is complete, tell the status bar about it
        mNotificationHelper.completed();
    }
}