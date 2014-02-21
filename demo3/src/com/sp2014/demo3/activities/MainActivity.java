package com.sp2014.demo3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.sp2014.demo3.R;
import com.sp2014.demo3.data.ImageAdapter;
import com.sp2014.demo3.fragments.PhotoDialogFragment;
import com.sp2014.demo3.fragments.PhotoDialogFragment.NoticeDialogListener;

public class MainActivity extends FragmentActivity implements OnClickListener, NoticeDialogListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    GridView gridview = (GridView) findViewById(R.id.grid);
	    gridview.setAdapter(new ImageAdapter(this));
	    
	    Button btnPhoto = (Button)findViewById(R.id.btnPhoto);
	    btnPhoto.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
	 new PhotoDialogFragment().show(getSupportFragmentManager(), "");
		
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
