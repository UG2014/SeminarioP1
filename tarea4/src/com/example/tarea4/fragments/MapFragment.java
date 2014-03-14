package com.example.tarea4.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarea4.R;
import com.example.tarea4.activities.InfoTiendasActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapLongClickListener, InfoWindowAdapter	 {
	GoogleMap map;
	MarkerOptions marker1;
	View view;
	int i;
	RadioGroup radiogroup;
	//ArrayList<LatLng> tiendas = new ArrayList<LatLng>();
	
	LatLng[] tiendas ;
	
	public static final LatLng GUATE = new LatLng(14.62,-90.56);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		    view =inflater.inflate(R.layout.fragment_maps, container, false);
		    radiogroup = (RadioGroup) view.findViewById(R.id.radioGroup1);
		    map = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mapTAB)).getMap();
			map.setMyLocationEnabled(true); 
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(GUATE, 10));
			radiogroup.setOnCheckedChangeListener(checkedChangeListener);	
			
			final LatLng TIENDA_VINOS = new LatLng(14.8223,-90.5456);
			final LatLng TIENDA_LEGO = new LatLng(14.7243,-90.766);
			final LatLng TIENDA_LIBROS = new LatLng(14.92544,-90.8956);
			final LatLng TIENDA_ZAPATOS = new LatLng(17.7255d,-90.3456);
			final LatLng TIENDA_ROPA = new LatLng(14.34562,-90.456);
			final LatLng TIENDA_VINOS_2 = new LatLng(10.165672,-90.4456);
			final LatLng TIENDA_LIBROS_2 = new LatLng(19.33452,-91.56);
			final LatLng TIENDA_ROPA_2 = new LatLng(14.11232,-90.236);
		
			tiendas= new LatLng[]{TIENDA_VINOS,TIENDA_LEGO,TIENDA_LIBROS,TIENDA_ZAPATOS,TIENDA_ROPA,TIENDA_VINOS_2,TIENDA_LIBROS_2,TIENDA_ROPA_2};

			
			
			
		return   view;
				
	}
	OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			String option = "";
			switch (checkedId){
			case R.id.radio0:
				option ="Nomal";
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				break;
			case R.id.radio1:
				option ="Satelital";
				  map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				break;
			case R.id.radio2:
				option ="Hibrido";
				map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				break;
			case R.id.radio3:
				option ="Terreno";
				  map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				break;
			
			}
			Toast.makeText(getActivity(), option, Toast.LENGTH_SHORT).show();
			Log.e("SELECT",option);
		}
	}; 
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		MarkerOptions options; 
		
		for(i=0;i<tiendas.length;i++){
		options = new MarkerOptions().position(tiendas[i]).title("TIENDA "+ i).snippet(String.valueOf(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).anchor(0.5f, 0.5f);
		map.setInfoWindowAdapter(this);
		//map.setOnInfoWindowClickListener(listener);
		map.addMarker(options);
        
		}
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker marker) {
				
				Intent intent =  new Intent(getActivity(), InfoTiendasActivity.class); 
				intent.putExtra(InfoTiendasActivity.numberTienda, marker.getSnippet());
				startActivity(intent);
			}
		});
	}


	@Override
	public View getInfoContents(final Marker marker) {
		View window = getActivity().getLayoutInflater().inflate(R.layout.info_window,null);
		TextView txtTitle =(TextView)window.findViewById(R.id.txt_title);
		
			
		txtTitle.setText(marker.getTitle());
		
		return window;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		return null;
	}

	@Override
	public void onMapLongClick(LatLng arg0) {
		
	}
	
	
}