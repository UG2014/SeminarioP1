package com.seminarioP1.demo4;

import java.util.ArrayList;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.seminarioP1.demo4.data.DBAdapter;
import com.seminarioP1.demo4.data.Place;


public class App extends Application {
	private ArrayList<Place> places;
	private DBAdapter db;
	private RequestQueue requestQueue;
	private LruCache<Place, Bitmap>thumbail;
	public static final int CACHE_SIZE_BYTES= 2*1024*1024;
	
	@Override
	public void onCreate() {
		super.onCreate();
		db = new DBAdapter(this);
		places = db.getPlaces();
		thumbail= (new LruCache<Place, Bitmap>(CACHE_SIZE_BYTES) {
            
			@Override
            protected int sizeOf(Place key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();

            }});     		

		requestQueue = Volley.newRequestQueue(this);
		requestQueue.start();
	}

	public ArrayList<Place> getPlaces() {
		return places;
	}



	public RequestQueue getRequestQueue() {
		return requestQueue;
	}

	public DBAdapter getDB() {
		return db;
	}

	public LruCache<Place, Bitmap> getThumbail() {
		return thumbail;
	}


	
	
}
