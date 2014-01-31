package com.sp1.demo1;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {
Button btnSearch;
Button btnOpenAct;
Button btnList;
ScrollView inputControls;

public static final  String TAG = MainActivity.class.toString();
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	btnSearch=(Button) findViewById(R.id.btnSearch);
	btnOpenAct=(Button) findViewById(R.id.btnOpen);
	btnList=(Button) findViewById(R.id.btnList);
	btnSearch.setOnClickListener(new ButtonListener());
	btnOpenAct.setOnClickListener(new ButtonListener());
	btnList.setOnClickListener(new ButtonListener());
	/*
	Button btnList = new Button(this);
	btnList.setText(getResources().getString(R.string.btn_list));
	//btnList.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
	mainConten.addView(btnList);
	*/
	LinearLayout mainConten =(LinearLayout)findViewById(R.id.mainConten);
	
    inputControls = (ScrollView) View.inflate(this,R.layout.input_controls_contents, null);
	setInputControls();
    mainConten.addView(inputControls);
	}

	public void setInputControls(){
		SeekBar    seekbar    = (SeekBar) inputControls.findViewById(R.id.seekBar1);
		RatingBar  ratingBar  = (RatingBar) inputControls.findViewById(R.id.ratingBar1);
		Spinner    spinner    = (Spinner)inputControls.findViewById(R.id.spinner1);
		CheckBox   checkbox   = (CheckBox) inputControls.findViewById(R.id.checkBox1);
		RadioGroup radiogroup = (RadioGroup) inputControls.findViewById(R.id.radioGroup1);
		
		OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String option = "";
				switch (checkedId){
				case R.id.radio0:
					option ="A";
					break;
				case R.id.radio1:
					option ="B";
					break;
				case R.id.radio2:
					option ="C";
					break;
				
				}
				Log.e(TAG,"seleccionado "+ option);
			}
		}; 
		
		radiogroup.setOnCheckedChangeListener(checkedChangeListener);
		
		checkbox.setChecked(true);
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("HOLA");
		names.add("MUNDO");
		names.add("ADIOS");
		ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, names);
		spinner.setAdapter(namesAdapter);
		
		ratingBar.setRating((float)2.5);
		
		seekbar.setMax(10);
		seekbar.setProgress(5);
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
			
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			
				Toast.makeText(getApplicationContext(), "cambio a " + progress, Toast.LENGTH_LONG).show();
			}
		});
		
		
		
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			EditText searchQ = (EditText)findViewById(R.id.editTextSerch);
			String Searchtext =searchQ.getText().toString();
			String url = "https://www.google.com/?q="+Searchtext+"#q="+Searchtext;
			Intent inten= null;
			if (v.getId() == btnOpenAct.getId()){
				 inten = new Intent(getApplicationContext(), Activity_search.class);
				inten.putExtra(Activity_search.query,Searchtext);
				
			}else if (v.getId() == btnList.getId()){
				 inten = new Intent(getApplicationContext(), Email_Activity.class);
				
				
			}else if (v.getId()== btnSearch.getId()) {
				inten = new Intent(Intent.ACTION_VIEW);
				inten.setData(Uri.parse(url));
			}
			startActivity(inten);	
			
		}
		
	}

}
