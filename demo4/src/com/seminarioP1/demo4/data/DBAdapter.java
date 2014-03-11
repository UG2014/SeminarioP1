package com.seminarioP1.demo4.data;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	private DBHelper dbHelper;
	private static final String DATABASE_NAME= "places.db";
	private static final int DATABASE_VERSION =2 ;
	
	public DBAdapter (Context context){
		dbHelper = new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public void insert (Place p){
		ContentValues values = buildFromPlace(p);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
		db.insertWithOnConflict(DBHelper.PLACES_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		} finally{
		db.close();}
		
	}

	public void update (Place p){
		ContentValues values = buildFromPlace(p);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
		db.updateWithOnConflict(DBHelper.PLACES_TABLE, values,
				DBHelper.KEY_ID+"=?", new String[]{p.getId()+""}, SQLiteDatabase.CONFLICT_IGNORE);
		} finally{
		db.close();}
		
	}
	public int getTotalPlaces(){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(DBHelper.PLACES_TABLE	, null, null, null, null, null, null);
		int total =cursor.getCount();
		db.close();
		cursor.close();
		return total;
	}
	
	public void deleteAll(){
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
		db.delete(DBHelper.PLACES_TABLE,null,null);
		} finally{
		db.close();}
	}
	
	public ArrayList<Place> getPlaces(){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(DBHelper.PLACES_TABLE	, null, null, null, null, null, null);
		ArrayList<Place> places = new ArrayList<Place>();
		
		while (cursor.moveToLast()){
			Place p = new Place();
			p.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID)));
			p.setDate(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DATE)));
			p.setTime(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIME)));
			p.setAuthor(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_AUTHOR)));
			p.setThumbnailURL(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_URL)));
			p.setLoacalitation(new LatLng(cursor.getDouble(cursor.getColumnIndex(DBHelper.KEY_LATITUDE)), cursor.getDouble(cursor.getColumnIndex(DBHelper.KEY_LONGITUDE))));
			places.add(p);
			
		}
		db.close();
		cursor.close();
		
		return places;
	}
	public ContentValues buildFromPlace(Place p){
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_ID, p.getId());
		values.put(DBHelper.KEY_DATE, p.getDate());
		values.put(DBHelper.KEY_TIME, p.getTime());
		values.put(DBHelper.KEY_AUTHOR, p.getAuthor());
		values.put(DBHelper.KEY_LATITUDE, p.getLoacalitation().latitude);
		values.put(DBHelper.KEY_LONGITUDE, p.getLoacalitation().longitude);
		values.put(DBHelper.KEY_AUTHOR, p.getAuthor());
		values.put(DBHelper.KEY_URL, p.getThumbnailURL());
		
		return values;
		
	}
	
}
