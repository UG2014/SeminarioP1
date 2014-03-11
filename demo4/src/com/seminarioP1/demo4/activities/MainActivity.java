package com.seminarioP1.demo4.activities;

import android.app.Dialog;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.seminarioP1.demo4.App;
import com.seminarioP1.demo4.R;
import com.seminarioP1.demo4.data.DBAdapter;
import com.seminarioP1.demo4.fragments.ErrorDialogFragment;
import com.seminarioP1.demo4.fragments.PlacesFragment;

public class MainActivity extends FragmentActivity implements OnConnectionFailedListener, ConnectionCallbacks, LocationListener, OnClickListener {
	
	private LocationClient locationClient;
	private LocationRequest locationRequest;
	public final static  int  CONNECTION_FAILURE_REQUEST = 9000;
	public static final int MILLISECONDS_PER_SECOND = 1000;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = MILLISECONDS_PER_SECOND * 5;
    public static final long FAST_INTERVAL_CEILING_IN_MILLISECONDS = MILLISECONDS_PER_SECOND * 1;	
    public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		locationClient = new LocationClient(this, this, this);
		locationRequest = LocationRequest.create();
		locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);	
		locationRequest.setFastestInterval(FAST_INTERVAL_CEILING_IN_MILLISECONDS);
		Button btnDelet = (Button)findViewById(R.id.btnDelet);
		btnDelet.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		FragmentManager manager = getSupportFragmentManager();
		PlacesFragment fragment =(PlacesFragment)manager.findFragmentById(R.id.fragmentMap);
		if(servicesConnected()){
			manager.beginTransaction().show(fragment).commit();
		}else{
			manager.beginTransaction().hide(fragment).commit();
		}
	}

	private boolean servicesConnected() {
			int code = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
			if (code == ConnectionResult.SUCCESS) {
				return true;
			}else{
				Dialog dialog = GooglePlayServicesUtil.getErrorDialog(code, this, 0);
				
				if (dialog != null){
					ErrorDialogFragment fragment = new ErrorDialogFragment();
					fragment.setDialog(dialog);
					fragment.show(getSupportFragmentManager(), "error");
					}
				return false;
			}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onConnectionFailed(ConnectionResult ConnectionResult) {
		if(ConnectionResult.hasResolution()){
			try {
				ConnectionResult.startResolutionForResult(this, CONNECTION_FAILURE_REQUEST);
			} catch (IntentSender.SendIntentException e) {
				Log.e("ERROR",Log.getStackTraceString(e));

			}
				
		}else{
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(ConnectionResult.getErrorCode(), this, CONNECTION_FAILURE_REQUEST);
			
			if (dialog != null){
				ErrorDialogFragment fragment = new ErrorDialogFragment();
				fragment.setDialog(dialog);
				fragment.show(getSupportFragmentManager(), "error");
				}
			
		}
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
	updateLocation(locationClient.getLastLocation());
	locationClient.requestLocationUpdates(locationRequest, this);
		
	}

	private void updateLocation(Location currentLocation) {
		
			TextView txtLatLng = (TextView)findViewById(R.id.txtLatLng);
			
			String LatLng = "No disponible";
			if (currentLocation != null){
				LatLng = getString(R.string.txt_lat_lng,currentLocation.getLatitude(),currentLocation.getLongitude());
				
			}
			txtLatLng.setText(LatLng);
		
	}

	@Override
	public void onDisconnected() {
	
	}

	@Override
	protected void onStart() {
		super.onStart();
		locationClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (locationClient.isConnected()){
			locationClient.removeLocationUpdates(this);
			
		}
		locationClient.disconnect();	
	}

	@Override
	public void onLocationChanged(Location location) {
	//	Log.e("ACTUALIZACION", location.toString());
		updateLocation(location);
		
	}

	@Override
	public void onClick(View arg0) {
		DBAdapter db = ((App)getApplicationContext()).getDB();
		db.deleteAll();
		
		FragmentManager manager = getSupportFragmentManager();
		PlacesFragment fragment = (PlacesFragment)manager.findFragmentById(R.id.fragmentMap);
		fragment.removeAllMarkers();
	}
	

}
