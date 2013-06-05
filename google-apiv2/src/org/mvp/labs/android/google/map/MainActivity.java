package org.mvp.labs.android.google.map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	static final LatLng LA_PAZ = new LatLng(-16.518973, -68.120728);
	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		
		Log.v("about INFO......", GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this));
		
		Marker anywhere = map.addMarker(new MarkerOptions().position(LA_PAZ)
				.title("Anywhere"));

		// Move the camera instantly to anywhere with a zoom of 9.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(LA_PAZ, 9));

		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(-16.512985, -68.120883)));
		map.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}