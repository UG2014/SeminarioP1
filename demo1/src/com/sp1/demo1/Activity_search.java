package com.sp1.demo1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.util.Linkify;
import android.view.Menu;
import android.widget.TextView;

public class Activity_search extends Activity {
	public static final String query = "query";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_search);
	Intent intent = getIntent();
	String qtext= intent.getStringExtra(query);
	if (qtext != null ){
		TextView txtm = (TextView)findViewById(R.id.txtMsg);
			txtm.setText(qtext);
			Linkify.addLinks(txtm, Linkify.ALL);
	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_activity_search, menu);
		return true;
	}

}
