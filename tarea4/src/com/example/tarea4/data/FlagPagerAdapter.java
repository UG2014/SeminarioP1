package com.example.tarea4.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tarea4.R;
import com.example.tarea4.fragments.FragmentImages;

public class FlagPagerAdapter extends FragmentPagerAdapter {
	private int[] arrayFlags= new int[]{
			R.drawable.im,
			R.drawable.im2,
			R.drawable.im3,
			R.drawable.im4,
			R.drawable.im5,
			R.drawable.im6,
			R.drawable.im7,
			R.drawable.im8,
			R.drawable.im9,
			R.drawable.im10};

	public FlagPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int position) {
	
		
		Fragment fragment =new FragmentImages();
		Bundle args=new Bundle();
		args.putInt(FragmentImages.RESOURCE, arrayFlags[position]);
		
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {

		return arrayFlags.length;
	}

}
