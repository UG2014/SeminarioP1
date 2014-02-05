package com.example.sp1.tarea1;

import java.util.ArrayList;
import java.util.Arrays;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements
OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] arrayCountries =  new String[]{"Tienda de vinos","Tienda de Lego","Tienda de Libros","Tienda de Zapatos","Tienda de Ropa","Tienda de vinos","Tienda de libros","Tienda de Ropa"};
		
		ArrayList<String> countries =new ArrayList<String>(Arrays.asList(arrayCountries));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,countries);
		
		ListView list = (ListView)findViewById(R.id.listView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
			 
			Intent intent =  new Intent(this, InfoTiendasActivity.class);
			intent.putExtra(InfoTiendasActivity.numberTienda, Integer.toString(position));
			startActivity(intent);
		
	}

}
