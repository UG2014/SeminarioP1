package com.example.tarea4.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


public class PlaceFragment extends SupportMapFragment {
	GoogleMap map;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
	}
	

	
	@Override
	public void onResume() {
		super.onResume();
		setupMap();
	}
	private void setupMap() {
		if (map==null){
			map= getMap();
			if(map != null){
				
					//map.moveCamera(CameraUpdateFactory.newLatLngZoom(GUATE, 10));
					//map.setOnMapLongClickListener(this);
					map.setMyLocationEnabled(true);
					//map.setInfoWindowAdapter(this);
				map.getUiSettings().setZoomControlsEnabled(false);
			}
			
		}
		
	}

}
