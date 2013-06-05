package org.mvp.labs.android.google.map;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends MapActivity {
	private MapController mapController;
	private MapView mapView;
	private GeoPoint p;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main); // bind the layout to the activity

		// create a map view
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		mapController = mapView.getController();

		Geocoder geoCoder = new Geocoder(this, Locale.getDefault());  

		mapController.setZoom(18);

		p = getPoint(-16.504272, -68.131253);
		mapController.animateTo(p);

		//---Add a location marker---
		MapOverlay mapOverlay = new MapOverlay();
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		listOfOverlays.add(mapOverlay);      

		mapView.invalidate();
	}  

	private GeoPoint getPoint(double lat, double lon) {
		return (new GeoPoint((int) (lat * 1e6), (int) (lon * 1e6)));
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	class MapOverlay extends com.google.android.maps.Overlay
	{
		@Override
		public boolean draw(Canvas canvas, MapView mapView,
				boolean shadow, long when)
		{
			super.draw(canvas, mapView, shadow);                 

			//---translate the GeoPoint to screen pixels---
			Point screenPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);

			//---add the marker---
			Bitmap bmp = BitmapFactory.decodeResource(
					getResources(), R.drawable.pushpin);          
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);       
			return true;
		}

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView)
		{ 
			//---when user lifts his finger---
			if (event.getAction() == 1) {              
				GeoPoint p = mapView.getProjection().fromPixels(
						(int) event.getX(),
						(int) event.getY());

				Geocoder geoCoder = new Geocoder(
						getBaseContext(), Locale.getDefault());
				try {
					List<Address> addresses = geoCoder.getFromLocation(
							p.getLatitudeE6()  / 1E6,
							p.getLongitudeE6() / 1E6, 1);

					String add = "";
					if (addresses.size() > 0)
					{
						for (int i=0; i<addresses.get(0).getMaxAddressLineIndex();
								i++)
							add += addresses.get(0).getAddressLine(i) + "\n";
					}

					Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
				}
				catch (IOException e) {              
					e.printStackTrace();
				} 
				return true;
			}
			else              
				return false;
		}      
	}
}
