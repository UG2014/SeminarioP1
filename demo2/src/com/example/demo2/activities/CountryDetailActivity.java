package com.example.demo2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.demo2.R;

public class CountryDetailActivity extends FragmentActivity {
	private String country = "";
	public static final String COUNTRY = "country";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_country_detail);
		Intent intent = getIntent();
		country = intent.getStringExtra(COUNTRY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_help:	
			return true;
		case R.id.action_share:
			if (!country.equals("")) {
				String url = "http://es.m.wikipedia.org/wiki/" + country;
				String msg = getString(R.string.msg_share, country, url); 
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);	
				intent.putExtra(Intent.EXTRA_TEXT,msg);
				intent.setType("text/plain");
				startActivity(Intent.createChooser(intent, getResources().getText(R.string.action_share)));
			}
			return true;
		default :
			return super.onOptionsItemSelected(item);
		}	
	
	}
	
	public String getCountry() {
		return country;
	}

	

}
