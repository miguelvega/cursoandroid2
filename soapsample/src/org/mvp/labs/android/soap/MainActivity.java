package org.mvp.labs.android.soap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView tv = (TextView)findViewById(R.id.resultView);
		
		SOAPClient client = new SOAPClient(new SOAPClient.Receiver() {
			@Override
			public void onLoad(String input) {
				tv.setText(input);
			}
		});
		client.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
