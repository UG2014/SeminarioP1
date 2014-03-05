package com.example.tarea3.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.tarea3.R;
import com.example.tarea3.fragments.ComenttFragment;

public class ComentesMarketActivity extends android.support.v4.app.FragmentActivity {
	private Fragment fragments = new  ComenttFragment();
	  Drawable drawable = null;
	  String draw="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comentes_market);
		ImageView view = (ImageView)findViewById(R.id.imageView1);
		 Intent intent = getIntent();
		String	Tienda = intent.getStringExtra("id");
		 Resources res = getResources();
	       drawable = null;
		
		if (Tienda.equals("0")){
			 drawable = res.getDrawable(R.drawable.vinos);
			 draw="R.drawable.vinos";
		}else if (Tienda.equals("1")){
			 drawable = res.getDrawable(R.drawable.lego);
			 draw="R.drawable.lego";
		}else if (Tienda.equals("2")){
			 drawable = res.getDrawable(R.drawable.libro);
			 draw="R.drawable.libro";
		}else if (Tienda.equals("3")){
			 drawable = res.getDrawable(R.drawable.zapatos);
			 draw="R.drawable.zapatos";
		}else if (Tienda.equals("4")){
			 drawable = res.getDrawable(R.drawable.ropa);
			 draw="R.drawable.ropa";
		}else if (Tienda.equals("5")){
			 drawable = res.getDrawable(R.drawable.vinos2);
			 draw="R.drawable.vinos2";
		}else if (Tienda.equals("6")){
			 drawable = res.getDrawable(R.drawable.libro2);
			 draw="R.drawable.libro2";
		}else if (Tienda.equals("7")){
			 drawable = res.getDrawable(R.drawable.ropa2);
			 draw="R.drawable.ropa2";
		}
		
		
		view.setImageDrawable(drawable);
		
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().add(R.id.comentFrame, fragments).commit();
		
//	TextView txt = (TextView)findViewById(R.id.textView1);
	//txt.setText("Descripcion: la tienda X se dedica a \n desde el anio 0000 a trabajado en ......");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.infomarket_menu, menu);
		return true;
	}

public boolean onOptionsItemSelected(MenuItem item){
		
		
		switch (item.getItemId()) {
		
		case R.id.menu_favorito:	
			return true;
		case R.id.menu_share:
			
			        
			 
			          Intent intent = new Intent();
			          intent.setAction(Intent.ACTION_SEND);
			          intent.putExtra(Intent.EXTRA_STREAM, R.drawable.vinos);
			          intent.setType("image/jpeg");
			          startActivity(Intent.createChooser(intent, "Shared Image"));
			 
			    return true;

		default :
			return super.onOptionsItemSelected(item);
		
		
		
		
		
	}
}
}
