package com.example.tarea3.fragments;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tarea3.R;
import com.example.tarea3.activities.ListImages;
import com.example.tarea3.activities.MainActivity;
import com.example.tarea3.data.BitmapLRUCache;
import com.example.tarea3.data.Helper;
import com.example.tarea3.data.ListAdapter;




public class ComunityFragment extends Fragment implements OnClickListener, com.example.tarea3.fragments.PhotoDialogFragment.NoticeDialogListener {
	private ArrayList<ListImages> photosList; //animales
	private ListView lvPhotos; //lvanimales
	private ListAdapter adapter;
	private ImageButton btnPhoto ;
	private ImageLoader imageLoader; 
	private static final int LOAD_IMAGE =1;
	private static final int CAMARE =2;
	int code =0;
	String photoPath;
	public static RequestQueue requestQueue;
	//private TextView tvNombre;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.fragment_comunity, container, false);
		photosList = new ArrayList<ListImages>();
		requestQueue= Volley.newRequestQueue(getActivity());
		//rellenarArrayList();
		btnPhoto = (ImageButton)view.findViewById(R.id.buttonCamera);
		//tvNombre = (TextView) view.findViewById(R.id.Coment);
		 btnPhoto.setOnClickListener(this);
		imageLoader = new ImageLoader(requestQueue, new BitmapLRUCache());
		adapter = new ListAdapter(getActivity(), photosList);
		
		lvPhotos = (ListView) view.findViewById(R.id.listViewComunity);
		APICall();
		return   view;
				
	}
	
	private void APICall() {
		//btnUpdate.setEnabled(false);
		String url = Helper.getRecentMediaUrl("guatemala");
		//findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
		
		Response.Listener<JSONObject> listener =new Response.Listener<JSONObject>(){

			@Override
			public void onResponse(JSONObject responce) {
				//findViewById(R.id.progressBar).setVisibility(View.GONE);
				//findViewById(R.id.grid).setVisibility(View.VISIBLE);
				//btnUpdate.setEnabled(true);
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

	            		//	setImageUrl(imgUrl, imageLoader);
							
						}
					}
	            	//showNotification();
	            	adapter.notifyDataSetChanged();
	            	
				}catch (JSONException e){
					Log.e("ERROR:",Log.getStackTraceString(e));
				}
			}
			
		}; 
		
		
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null,listener,null);
		requestQueue.add(jsObjRequest);
		
	}

	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lvPhotos.setAdapter(adapter);
		// Asignamos el Listener al ListView para cuando pulsamos sobre uno de
		// sus items.
		//lvAnimales.setOnItemClickListener(this);
	
	
	}
	@Override
	public void onDialogPositiveClick() {
		Toast.makeText(getActivity(), "CAMARA", Toast.LENGTH_SHORT).show();
		
		
	}

	@Override
	public void onDialogNegativeClick() {
		Toast.makeText(getActivity(), "GALERIA", Toast.LENGTH_SHORT).show();
		
	}
	
	public void onClick(View v) {
		if (v.getId()==btnPhoto.getId()){
	// new PhotoDialogFragment().show(getActivity().getSupportFragmentManager(), "");
			AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
			 
			dialog.setMessage("¿Tomar Foto?");
			dialog.setCancelable(false);
			dialog.setPositiveButton("Camara", new DialogInterface.OnClickListener() {
			 
			  @Override
			  public void onClick(DialogInterface dialog, int which) {
			     //NotificacionesActivity.this.finish();
				  Toast.makeText(getActivity(), "CAMARA", Toast.LENGTH_SHORT).show();  
				 Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File photo= setupFile();
					 photoPath = photo.getAbsolutePath();
					intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
					code=CAMARE;
					startActivityForResult(intent, code);
		  
			  }
			});
			dialog.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
			 
			   @Override
			    public void onClick(DialogInterface dialog, int which) {
			      //dialog.cancel();
				   Toast.makeText(getActivity(), "GALERIA", Toast.LENGTH_SHORT).show();
					  Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						code=LOAD_IMAGE;
						startActivityForResult(intent, code);
			   }
			});
			dialog.show();
		}
	}
	
	private File setupFile() {
		File albumDir;
		String albumName="ejemplo";
		if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.FROYO){
				albumDir= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),albumName);
			}else {
				albumDir=new File(Environment.getExternalStorageDirectory() + "/dcmi"+albumName);
			}
		albumDir.mkdirs();
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Calendar.getInstance().getTime());
		String imageFileName = "IMG_"+ timeStamp +".jpg";
		File image = new File(albumDir+"/"+imageFileName);
		return image;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
				super.onActivityResult(requestCode, resultCode, data);
				
				switch (requestCode){
				case LOAD_IMAGE:
					if (resultCode == getActivity().RESULT_OK){
						fromGallery(data);
					}
					break;
				case CAMARE:
					if (resultCode == getActivity().RESULT_OK){
						fromCamera(data);
					}
					break;
				}
	}
	
	public void fromGallery (Intent data){
		if(data != null){
			Uri selectedImage = data.getData();
			String[] filePathColumn ={MediaStore.Images.Media.DATA};
			Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn,null,null,null);
			
			if (cursor.moveToFirst()){
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				Log.e("PICTUREPATH",picturePath);
				cursor.close();
				//Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Calendar.getInstance().getTime());
				photosList.add(new ListImages(timeStamp,picturePath,0 ));
				//code 0 gallery
				adapter.notifyDataSetChanged();	
				
				//ImageView img =(ImageView)findViewById(R.id.img);
				//img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
				
			}
			
			
		}
	}	
	
	public void fromCamera (Intent data){
		
		//ImageView img =(ImageView)findViewById(R.id.img);
		//Bitmap bitmap = resizeBitmap(img.getWidth(), img.getHeight());
		//img.setImageBitmap(bitmap);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Calendar.getInstance().getTime());
		photosList.add(new ListImages(timeStamp,photoPath,1 ));
		// code 1 camera
		/*Intent mediaScan =new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File file =new File(photoPath);
		Uri contentUri = Uri.fromFile(file);
		mediaScan.setData(contentUri);
		getActivity().sendBroadcast(mediaScan);*/
		adapter.notifyDataSetChanged();
	}
	

	  
	  
	  
	/*private void rellenarArrayList() {
		photosList.add(new ListImages("aguila", R.drawable.ic_action_user));
		photosList.add(new ListImages("ballena", R.drawable.ic_action_user));
		photosList.add(new ListImages("caballo", R.drawable.ic_action_user));
		photosList.add(new ListImages("aguila", R.drawable.ic_action_user));
		photosList.add(new ListImages("ballena", R.drawable.ic_action_user));
		photosList.add(new ListImages("caballo", R.drawable.ic_action_user));
		photosList.add(new ListImages("aguila", R.drawable.ic_action_user));
		
	}*/
	
}