package com.seminarioP1.demo4.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seminarioP1.demo4.App;
import com.seminarioP1.demo4.R;
import com.seminarioP1.demo4.data.DBAdapter;
import com.seminarioP1.demo4.data.Helper;
import com.seminarioP1.demo4.data.Place;

public class PlacesFragment extends SupportMapFragment implements OnMapLongClickListener, InfoWindowAdapter	 {
	private GoogleMap map; 
	private DBAdapter db;
	private ArrayList<Place> places;
	private Bundle saveInstanceState; 
	public static final LatLng GUATE = new LatLng(14.62,-90.56);
	private HashMap<Marker, Place> markers = new HashMap<Marker, Place>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.saveInstanceState = savedInstanceState;
		places = ((App)getActivity().getApplicationContext()).getPlaces();
		db = ((App)getActivity().getApplicationContext()).getDB();
	}
	
	public void removeAllMarkers(){
		places.clear();
		map.clear();
		markers.clear();
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
				if (saveInstanceState == null){
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(GUATE, 10));
					map.setOnMapLongClickListener(this);
					map.setMyLocationEnabled(true);
					map.setInfoWindowAdapter(this);
					loadPlaces();
				}
				map.getUiSettings().setZoomControlsEnabled(false);
			}
			
		}
		
	}
	private void loadPlaces() {
		for (Place place :places){
			addPlaceTomap(place);
		}
	}
	@Override
	public void onMapLongClick(LatLng point) {

		Place place = createNewPlace(point);
		addPlaceTomap(place);
	
	} 
	
	public void addPlaceTomap(Place place){
		String title = getString(R.string.txt_marker_title,place.getDate());
		String snippet = getString(R.string.txt_marker_snippet,place.getTime());
		
		MarkerOptions options = new MarkerOptions().position(place.getLoacalitation()).title(title).snippet(snippet);
		
		Marker marker = map.addMarker(options);
		markers.put(marker, place);
		if (place.getThumbnailURL() != null){
		grabFromFlickr(marker, place);}else 
		{
			grabThumbnailImage(marker,place);
		}
		
	}
	public Place createNewPlace(LatLng location){
		String date = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(Calendar.getInstance().getTime());
		String time = new SimpleDateFormat("HH:mm",Locale.getDefault()).format(Calendar.getInstance().getTime());
		
	
		
		Place newPlace = new Place();
		newPlace.setId(places.size()+1);
		newPlace.setDate(date);
		newPlace.setTime(time);
		newPlace.setLoacalitation(location);
		places.add(newPlace);
		db.insert(newPlace);
		int total = db.getTotalPlaces();
		Log.i("AVISO","TOTAL: "+total);
		return newPlace;
	}
	
	@Override
	public View getInfoContents(Marker marker) {
		Place place = markers.get(marker);
		View window = getActivity().getLayoutInflater().inflate(R.layout.info_window,null);
		TextView txtTitle =(TextView)window.findViewById(R.id.txt_title);
		TextView txtSnippet =(TextView)window.findViewById(R.id.txt_snippet);
		ImageView imgThumbnail =(ImageView)window.findViewById(R.id.imageThumbnail);
		
		String snippett = marker.getSnippet();
		String author = place.getAuthor();
		if(author != null){
			snippett += "\n"+ author;
		}
		final LruCache<Place, Bitmap> thubnails =((App)getActivity().getApplicationContext()).getThumbail();
		Bitmap bitmap = thubnails.get(place);
		if (bitmap != null){
			imgThumbnail.setImageBitmap(bitmap);
		}
		txtTitle.setText(marker.getTitle());
		txtSnippet.setText(snippett);
		return window;
	}
	@Override
	public View getInfoWindow(Marker marker) {
		return null;
	}
	
	public void grabFromFlickr(final Marker marker, final Place place){
		String url = Helper.FLICKR_API_URL;
		final RequestQueue queue = ((App)getActivity().getApplicationContext()).getRequestQueue();
		
		Listener<JSONObject> listener = new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				try {
					
					JSONArray items = response.getJSONArray("items");			            	
	            	JSONObject media = items.getJSONObject(0).getJSONObject("media");
	            	String url = media.getString("m");
	            	
	            	String author = items.getJSONObject(0).getString("author");
	            	Log.e("DATOSSSSSS", "URL: "+url +" AUTHOOR " + author);
	            	place.setAuthor(author);
	            	place.setThumbnailURL(url);
	            	db.update(place);
	            	places.set(place.getId()-1,place);
	            	grabThumbnailImage(marker,place);
				
				} catch (JSONException e) {

					e.printStackTrace();
				}
				
				
			}
		};
		ErrorListener error = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("ERROR API", error.getMessage());
				
			}
		};
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,listener,error );
		
		queue.add(request);
	}
	public void grabThumbnailImage(final Marker marker, final Place place){
		final LruCache<Place, Bitmap> thubnails =((App)getActivity().getApplicationContext()).getThumbail();
		final RequestQueue queue = ((App)getActivity().getApplicationContext()).getRequestQueue();
		
		if(thubnails.get(place)== null){
			queue.add(new ImageRequest(place.getThumbnailURL(), new Response.Listener<Bitmap>(){

				@Override
				public void onResponse(Bitmap bitmap) {
					thubnails.put(place, bitmap);
					if (marker.isInfoWindowShown()){
						marker.showInfoWindow();
						
					}
					
				}
				
			}, 256,256,Config.ARGB_4444,null));
		}
	}
	
}
