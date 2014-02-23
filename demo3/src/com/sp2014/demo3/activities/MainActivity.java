package com.sp2014.demo3.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sp2014.demo3.R;
import com.sp2014.demo3.data.Helper;
import com.sp2014.demo3.data.Image;
import com.sp2014.demo3.data.ImageAdapter;
import com.sp2014.demo3.fragments.PhotoDialogFragment;
import com.sp2014.demo3.fragments.PhotoDialogFragment.NoticeDialogListener;

public class MainActivity extends FragmentActivity implements OnClickListener, NoticeDialogListener {
	 Button btnParse ;
	 Button btnPhoto ;
	 Button btnUpdate ;
	 ImageAdapter adapter; 
	 ArrayList<Image> imagesArray;	
	public static RequestQueue requestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Parse.initialize(this, "l0DUqeE1ePH48FJLrRYkwdRHqNaB3rXP6KnEJ6Dg", "s5W6conYjTJrIwRGFzcuXRhqE2qQ7yRQrffTm4Vz");
		requestQueue= Volley.newRequestQueue(this);
	    GridView gridview = (GridView) findViewById(R.id.grid);
	    imagesArray = new ArrayList<Image>();
	     adapter =  new ImageAdapter(this,imagesArray);  
	    gridview.setAdapter(adapter);
	    
	     btnPhoto = (Button)findViewById(R.id.btnPhoto);
	    btnUpdate = (Button)findViewById(R.id.btnUpdate);
	    btnParse = (Button)findViewById(R.id.btnParse);
	    btnParse.setOnClickListener(this);
	    btnUpdate.setOnClickListener(this);
	    btnPhoto.setOnClickListener(this);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId()==btnPhoto.getId()){
	 new PhotoDialogFragment().show(getSupportFragmentManager(), "");
		}else if (v.getId()==btnUpdate.getId()){
			
			APICall();
		}else if (v.getId()==btnParse.getId()){
			
			parse();
		}
	}
	
	private void parse() {
		ParseObject test = new ParseObject("Prueba");
		test.put("nombre","JTR");
		test.saveInBackground();
		Log.e("TAG","GUARDANDO");
		//EqMpUF71CO	
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Prueba");	
		query.getInBackground("EqMpUF71CO", new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject obj, ParseException arg1) {
				if(obj != null){
					Toast.makeText(getApplicationContext(), obj.getString("nombre"),Toast.LENGTH_SHORT).show();
					
				}
			}
		});
	}

	public void showNotification() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher).setContentTitle(getString(R.string.txt_notif_title)).setContentText(getString(R.string.txt_notif_subtitle));

		Intent result = new Intent(this, CameraActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(CameraActivity.class);
		stackBuilder.addNextIntent(result);

		PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);		        

		NotificationManager mNotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		mNotificationManager.notify(1, mBuilder.build());	
	}

	private void APICall() {
		btnUpdate.setEnabled(false);
		String url = Helper.getRecentMediaUrl("guatemala");
		findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
		
		Response.Listener<JSONObject> listener =new Response.Listener<JSONObject>(){

			@Override
			public void onResponse(JSONObject responce) {
				findViewById(R.id.progressBar).setVisibility(View.GONE);
				findViewById(R.id.grid).setVisibility(View.VISIBLE);
				btnUpdate.setEnabled(true);
				JSONArray data;
            	
				try {
					data = responce.getJSONArray("data");
	            	for (int i = 0; i < data.length(); i++) {
	            		JSONObject currentElement = data.getJSONObject(i);
	            		String type = currentElement.getString("type");
						if (type.equals("image")){
							JSONObject user = currentElement.getJSONObject("user");
	            			JSONObject images = currentElement.getJSONObject("images");
	            			JSONObject standardResolution = images.getJSONObject("standard_resolution");

	            			String imgUrl = standardResolution.getString("url");
	            			String userName = user.getString("username");

	            			Image image = new Image();
	            			image.setImgUrl(imgUrl);
	            			image.setUserName(userName);
	            			imagesArray.add(image);
							
						}
					}
	            	showNotification();
	            	adapter.notifyDataSetChanged();
	            	
				}catch (JSONException e){
					Log.e("ERROR:",Log.getStackTraceString(e));
				}
			}
			
		}; 
		
		
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null,listener,null);
		requestQueue.add(jsObjRequest);
		
	}

	@Override
	public void onDialogPositiveClick() {
		Intent intent = new Intent(this,CameraActivity.class);
		startActivity(intent);
		
	}

	@Override
	public void onDialogNegativeClick() {
		Toast.makeText(this, "Click en NO", Toast.LENGTH_SHORT).show();
		
	}
	

	

}

