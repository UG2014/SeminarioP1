package com.example.tarea4.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.tarea4.R;


public class ComenttFragment extends Fragment implements
OnItemClickListener   {
	ListView list;
	
	@SuppressWarnings("unused")
	private final static String EMAIL = "email";
	@SuppressWarnings("unused")
	private final static String DATE_ADDED = "date";
	@SuppressWarnings("unused")
	private List<HashMap<String,String>> emails= new ArrayList<HashMap<String,String>>();
	Button btnAdd;
	EditText editTextEmail;
	SimpleAdapter adapter2;
	String email ="HOla";
	ArrayList<String> coments=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new  ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, coments);
		list.setAdapter(adapter);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view =inflater.inflate(R.layout.list_input_coment, container, false);
			list = (ListView)view.findViewById(R.id.ListC);
			editTextEmail = (EditText)view.findViewById(R.id.editEmail_coment);
			btnAdd = (Button)view.findViewById(R.id.btnAdd_coment);
			btnAdd.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					 email = editTextEmail.getText().toString();
					 if (!email.trim().equals("")){
					 coments.add(email);

					adapter.notifyDataSetChanged();
					
					 }
						
						
				}
				
				
			});	

			return   view;
				
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	
	}

	


	
	

