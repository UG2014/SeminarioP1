package com.example.tarea4.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tarea4.R;
import com.example.tarea4.data.DBAdapter;
import com.example.tarea4.fragments.ComenttFragment;

public class ComentesMarketActivity extends android.support.v4.app.FragmentActivity {
	private Fragment fragments = new  ComenttFragment();
	  Drawable drawable = null;
	  String draw="";
		private DBAdapter db;
	  String	Tienda;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comentes_market);
		ImageView view = (ImageView)findViewById(R.id.imageView1);
		 Intent intent = getIntent();
		  db = new DBAdapter(this);
		 Tienda = intent.getStringExtra("id");
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
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.infomarket_menu, menu);
		return true;
	}

public boolean onOptionsItemSelected(MenuItem item){
		
		
		switch (item.getItemId()) {
		
		case R.id.menu_fav:	
			if(db.getTotalFavTiendas(Tienda)<1){
				Toast.makeText(this, "INSERT", Toast.LENGTH_SHORT).show();
				int x =db.getTotalFavTiendas(Tienda) +1;
				String[] p = new String[]{Tienda,String.valueOf(x)};
				db.insert_fav(p);
				Toast.makeText(this, "INSERT", Toast.LENGTH_SHORT).show();
			}else{
				
				int x =db.getTotalFavTiendas(Tienda) +1;
				String[] p = new String[]{Tienda,String.valueOf(x)};
				Log.e("MAndo p1 ",p[1]);
				db.updateFAV(p);
				Toast.makeText(this, "NUEVOO "+String.valueOf(db.getTotalFavTiendas(Tienda)), Toast.LENGTH_SHORT).show();
			}
		
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
