package com.example.tarea4.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public final static String KEY_TIENDA_ID= "id";
	public final static String KEY_MSJ_ID= "id";
	public final static String KEY_TIENDA_NAME= "name";
	public final static String KEY_TIENDA_ADDRESS= "address";
	public final static String KEY_TIENDA_HORARIO= "horario";
	public final static String KEY_TIENDA_EMAIL= "email";
	public final static String KEY_TIENDA_WEB= "web";
	public final static String KEY_TIENDA_PHONE= "cel";
	public final static String KEY_TIENDA_MSJ= "MSJ";
	public final static String  KEY_NUM_FAV= "favoritos";
	public final static int KEY_TOTAL_FAV= 0;
	public final static String TIENDA_TABLE= "tienda";
	public final static String PHOTO="photo";
	public final static String FAV_TABLE= "favoritos";
	public final static String COMENTS_TABLE= "coment";

	public final static String DATABASE_CREATE = "CREATE TABLE " + TIENDA_TABLE +
			
			  "(" + KEY_TIENDA_ID + " integer primary key autoincrement, " 
			     +  KEY_TIENDA_NAME + " text, "
			     + KEY_TIENDA_ADDRESS + " text,"
			     + KEY_TIENDA_HORARIO + " text, " 
			     + KEY_TIENDA_EMAIL + " text,"
			     + KEY_TIENDA_WEB+ " text,"
			     + KEY_TIENDA_PHONE+ " text,"
			     + KEY_TIENDA_MSJ+ " text,"
			     +PHOTO+ " text)";

	
	public final static String DATABASE_CREATE_2 =
				"CREATE TABLE " + FAV_TABLE +
			
			  "(" + KEY_TIENDA_ID + " integer primary key autoincrement, "
			  	   +  KEY_NUM_FAV + " text)";
	public final static String DATABASE_CREATE_3 =
			"CREATE TABLE " + COMENTS_TABLE +
		
		  "(" + KEY_TIENDA_ID + " integer primary key autoincrement, " 
		     +  KEY_TIENDA_MSJ + " text)";

	
	
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE_2);
		db.execSQL(DATABASE_CREATE_3);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS "+TIENDA_TABLE);
		db.execSQL("DROP TABLE IF EXISTS "+FAV_TABLE);
		db.execSQL("DROP TABLE IF EXISTS "+COMENTS_TABLE);
		onCreate(db);
		
	}

}
