package com.example.tarea4.data;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAdapter {
	private DBHelper dbHelper;
	private static final String DATABASE_NAME= "tienda.db";
	private static final int DATABASE_VERSION =2 ;
	
	public DBAdapter (Context context){
		dbHelper =(new DBHelper(context, DATABASE_NAME, null, DATABASE_VERSION));
	}

	public DBHelper getDbHelper() {
		return dbHelper;
	}
	
	public void insert (String[] p){
		ContentValues values = buildFromPlace(p);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
		db.insertWithOnConflict(DBHelper.TIENDA_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		} finally{
		db.close();
		}
	}
	

	public int getTotalTiendas(String nTienda){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String[] args = new String[]{nTienda};
		Cursor cursor = db.query(DBHelper.TIENDA_TABLE	, null,DBHelper.KEY_TIENDA_ID+"=?", args, null, null, null);
		int total =cursor.getCount();
		db.close();
		cursor.close();
		return total;
	}
	
	public String[]  getInfoTienda(String nTienda){
		SQLiteDatabase db = dbHelper.getReadableDatabase(); 
		String[] args = new String[]{nTienda};
		
		Cursor cursor = db.query(DBHelper.TIENDA_TABLE	, null, DBHelper.KEY_TIENDA_ID+"=?", args, null, null, null);
		String[] tienda = null;
		while (cursor.moveToNext()){
			 tienda = new String[]{
					 cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIENDA_ID)),
					 cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIENDA_NAME)),
					 cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIENDA_ADDRESS)),
					 cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIENDA_EMAIL)),
					 cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIENDA_HORARIO)),
					 cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIENDA_PHONE)),
					 cursor.getString(cursor.getColumnIndex(DBHelper.KEY_TIENDA_WEB))
			 };
			
		}
		db.close();
		cursor.close();
		
		return tienda;
	}
	
	
	public ContentValues buildFromPlace(String[] p){
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_TIENDA_ID, p[0]);
		values.put(DBHelper.KEY_TIENDA_ADDRESS, p[1]);
		values.put(DBHelper.KEY_TIENDA_EMAIL, p[2]);
		values.put(DBHelper.KEY_TIENDA_HORARIO,p[3]);
		values.put(DBHelper.KEY_TIENDA_NAME, p[4]);
		values.put(DBHelper.KEY_TIENDA_PHONE, p[5]);
		values.put(DBHelper.KEY_TIENDA_WEB, p[6]);
		values.put(DBHelper.PHOTO, p[7]);
		
		return values;
		
	}

	////// FAVORITOS
	public int getTotalFavTiendas(String nTienda){
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String[] args = new String[]{nTienda};
		Cursor cursor = db.query(DBHelper.FAV_TABLE	, null,DBHelper.KEY_TIENDA_ID+"=?", args, null, null, null);
		int total =cursor.getCount();
		db.close();
		cursor.close();
		return total;
	}
	
	public ContentValues buildFromFav(String[] p){
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_TIENDA_ID, p[0]);
		values.put(DBHelper.KEY_NUM_FAV, p[1]);
		
		return values;
		
	}	

	public void insert_fav (String[] p){
		ContentValues values = buildFromFav(p);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
		db.insertWithOnConflict(DBHelper.FAV_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		} finally{
		db.close();
		}
	}
	
	 public void updateFAV (String[] p){
			ContentValues values = buildFromFav(p);
			
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			try {
			db.updateWithOnConflict(DBHelper.FAV_TABLE, values,
					DBHelper.KEY_TIENDA_ID+"=?",new String[]{p[0]}, SQLiteDatabase.CONFLICT_IGNORE);
			Log.e("adapre p0",p[0]);
			Log.e("adapre p1o",p[1]);
			} finally{
				
				db.close();
			}
			
		}
	
/* 
 *
	
	public void deleteAll(){
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		try {
		db.delete(DBHelper.PLACES_TABLE,null,null);
		} finally{
		db.close();}
	}
 * */
	
	
	
	
	
	
}
