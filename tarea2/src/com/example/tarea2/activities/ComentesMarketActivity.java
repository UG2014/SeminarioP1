package com.example.tarea2.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

import com.example.tarea2.R;
import com.example.tarea2.fragments.ComenttFragment;

public class ComentesMarketActivity extends android.support.v4.app.FragmentActivity {
	private Fragment fragments = new  ComenttFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comentes_market);
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

}
