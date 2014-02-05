package com.example.sp1.tarea1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ComentesMarketActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comentes_market);
	TextView txt = (TextView)findViewById(R.id.textView1);
	txt.setText("Descripcion: la tienda X se dedica a \n desde el anio 0000 a trabajado en ......");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_comentes_market, menu);
		return true;
	}

}
