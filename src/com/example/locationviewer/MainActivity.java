package com.example.locationviewer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	GPSTracker location;
	double latitude;
	double longitude;
	TextView showLatitude;
	TextView showLongitude;
	Button showLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		showLatitude = (TextView) findViewById(R.id.latitude);
		showLongitude = (TextView) findViewById(R.id.longitude);
		showLocation = (Button) findViewById(R.id.showlocation);
		location = new GPSTracker(this);
		
		showLocation.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (location.canGetLocation()){
					latitude = location.getLatitude();
					longitude = location.getLongitude();
					
					//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + 
					//		"\nLong: " + longitude, Toast.LENGTH_LONG).show();
					String lat = String.valueOf(latitude);
					String lon = String.valueOf(longitude);
					
					Log.v("",lat + " " + lon);
					
					//Set to textViews
					showLatitude.setText(lat);
					showLongitude.setText(lon);
				}
				else{
					//GPS or Network no available and ask user to turn on in setting
					location.showSettingsAlert();
				}
			}
		});	
			
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
