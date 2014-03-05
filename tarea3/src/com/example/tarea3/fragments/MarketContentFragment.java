package com.example.tarea3.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarea3.R;
import com.example.tarea3.activities.MainActivity;

public class MarketContentFragment extends Fragment  implements TabListener{
		Fragment[] fragment =new Fragment[]{new MarketListFragment(),new MapFragment()};
		
		
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
		final ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
			
			
			actionBar.addTab(actionBar.newTab().setText(getString(R.string.title_fragment_list)).setTabListener(this));
			actionBar.addTab(actionBar.newTab().setText(getString(R.string.title_fragment_flags)).setTabListener(this));
			
			FragmentManager manager =getActivity().getSupportFragmentManager();
			manager.beginTransaction().add(R.id.mainContent, fragment[0]).add(R.id.mainContent, fragment[1]).commit();
		
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			return inflater.inflate(R.layout.fragment_market_content, container,false);
		}

	
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {

			
		}
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			Fragment toHide =null;
			Fragment toShow =null;
			
			switch (tab.getPosition()){
			case 0:
					toHide=fragment[1];
					toShow=fragment[0];
					break;
			case 1:
				toHide=fragment[0];
				toShow=fragment[1];
				break;
			}
			ft.hide(toHide).show(toShow);
			
		}
		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {

			
		}
	}


